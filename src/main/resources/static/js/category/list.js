 addEventListener("DOMContentLoaded", (event) => { 
	//alert("test"); 
	let categoryList = [];
	let category = {};
	let divContentProducts = document.getElementById("content-products");
	
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
            //render();
        })
        .catch(error => {
            console.error("Error fetching categories:", error);
        });
    };
	
	const getCategoryWithChildrenCategoryAndProducts = () => {
		fetch(
			"/rest/categories/code/A", 
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
			//renderProductsByCategoryCode();
		}).catch(error => {
			console.error("Error fetching category by code: ", error);
		});
	}
	
	const renderProductsByCategoryCode = () => {
		divContentProducts.innerHTML = "";
		divContentProducts.classList.add("bg-danger");
	}
	
	/*
	********************************************
	* Init method declaration
	*/
	const init = () => {
		getCategoryList();
		getCategoryWithChildrenCategoryAndProducts();
	}
	
	/*
	********************************************
	* Init method
	*/
	init();
	
 });