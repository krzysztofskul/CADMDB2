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
    };
	
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
		clearContentProducts();
		
		// Create header for main category
		const headerMainCategory = document.createElement("ul");
		headerMainCategory.className = "category-header-js border";

		const categoryNamePL = document.createElement("h5");
		categoryNamePL.textContent = category.namePLplural;		
		headerMainCategory.appendChild(categoryNamePL);
		divContentProducts.appendChild(headerMainCategory);
		
		// Render subcategories recursively
		renderSubcategories(category, divContentProducts);
		
		function renderSubcategories(category, container) {
			
			// Loop through child categories
            category.categoryChildren.forEach((childCatgory) => {            
	            const header = document.createElement("li");
	            header.className = "category-card-js";
	            header.classList.add("border");

                const categoryNamePL = document.createElement("p");
                categoryNamePL.textContent = childCatgory.namePLplural;
                header.appendChild(categoryNamePL);
                
                // Append to the current container
                container.appendChild(header);

				// Recursively render children
	            if (childCatgory.categoryChildren && childCatgory.categoryChildren.length > 0) {
					const nestedContainer = document.createElement("ul"); // new sub-lis
					header.appendChild(nestedContainer);
					renderSubcategories(childCatgory, nestedContainer);
					//childCatgory.categoryChildren.forEach((subcategory) => {renderSubcategories(subcategory)});
				}

            });


        }
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