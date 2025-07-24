 addEventListener("DOMContentLoaded", (event) => { 
	//alert("test"); 
	let categoryList = [];
	let category = {};
	let divContentProducts = document.getElementById("content-products");
	let btnMenuList = Array.from(document.getElementsByClassName("btn-menu"));
	
	const setBtnMenuFucntionality = () => {
		btnMenuList.forEach(
			(btn)=> btn.addEventListener("click", (event)=> {
				event.preventDefault();
				//window.alert("TEST / button menu clicked! " + btn.dataset.categoryCode)
				category = btn.dataset.categoryCode;
				getCategoryWithChildrenCategoryAndProducts();
			})
		);
	}
	const getCategoryList = () => {
        fetch("/rest/categories/", {
            method: "GET",
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("TEST / Fetched categories:", data);
            //categoryList = data;
            //renderProductsByCategoryCode();
        })
        .catch(error => {
            console.error("Error fetching categories:", error);
        });
    } 
	const getCategoryWithChildrenCategoryAndProducts = () => {
		fetch(
			"/rest/categories/code/"+category, 
			{
				method: "GET",
				headers: { 'Content-Type': 'application/json' }
			}
		).then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
		}).then(data => {
			console.log("TEST / Fetched category:", data);
			//category = data;
			renderProductsByCategoryCode(data);
		}).catch(error => {
			console.error("Error fetching category by code: ", error);
		});
	}	
	const renderProductsByCategoryCode = (category) => {
		function renderSubcategories(category, container) {
			
			// Loop through child categories
            category.categoryChildren.forEach((childCatgory) => {            
	            const header = document.createElement("li");
	            header.className = "category-card-js";

                const categoryNamePL = document.createElement("h5");
                categoryNamePL.textContent = childCatgory.namePLplural;
                header.appendChild(categoryNamePL);
                
                // Append to the current container
                container.appendChild(header);
                
                // render products
				renderProductCard(childCatgory, header);

				// Recursively render children
	            if (childCatgory.categoryChildren && childCatgory.categoryChildren.length > 0) {
					const nestedContainer = document.createElement("ul"); // new sub-lis
					header.appendChild(nestedContainer);
					renderSubcategories(childCatgory, nestedContainer);
					//childCatgory.categoryChildren.forEach((subcategory) => {renderSubcategories(subcategory)});
				}

            });
        }		
		function renderProductCard(category, header) {
		    if (category.productList && category.productList.length > 0) {
					category.productList.forEach( (product)=> {
						//declare product card's info
						//card
						const productCard = document.createElement("a");
						productCard.className = "card-product-js card d-inline-block m-1 p-1 w-25";
						productCard.href = "/products/"+product.id+"?backToPage=/category/medical";
						productCard.style = "height:175px";
						//header
						const productCardHeader = document.createElement("div");
						productCardHeader.className = "card-header";
						productCardHeader.style = "height: 75px";
						productCardHeader.textContent = product.manufacturer.name+" "+product.modelName;
						//body
						const productCardBody = document.createElement("card-body");
						//price
						const productCardInfoPrice = document.createElement("p");
						const price = product.dataFinancial.price;
						const formattedPrice = new Intl.NumberFormat('en-US', {
						  style: 'currency',
						  currency: 'USD',
						}).format(price);
						productCardInfoPrice.textContent = formattedPrice;
						
						//build card
						productCard.appendChild(productCardHeader);
						productCard.appendChild(productCardBody);
						productCardBody.appendChild(productCardInfoPrice);						
						
						//add productCard to the header of the category
						header.appendChild(productCard);
					});
		
				}
			}
		function renderHeaderMainCategory(header) {			
			header.className = "category-header-js";
			const categoryNamePL = document.createElement("h4");
			categoryNamePL.className = "d-block";
			categoryNamePL.textContent = category.namePLplural;		
			header.appendChild(categoryNamePL);
			divContentProducts.appendChild(header);
		};
		
		//clear content before render product list by category
		clearContentProducts();
		
		// Create header for main category
		const headerMainCategory = document.createElement("ul");
		headerMainCategory.className = "d-block";
		renderHeaderMainCategory(headerMainCategory);
		
		// Render product for main category
		renderProductCard(category, headerMainCategory);
		
		// Render subcategories recursively
		renderSubcategories(category, headerMainCategory);
		
	}
	const clearContentProducts = () => {
		divContentProducts.innerHTML = "";
	}
	
	/*
	********************************************
	* Init method declaration
	*/
	const init = () => {
		setBtnMenuFucntionality();
	}
	
	/*
	********************************************
	* Init method
	*/
	init();
	
 });