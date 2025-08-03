document.addEventListener('DOMContentLoaded', () => {

	let btnCurrencyList = Array.from(document.getElementsByClassName("btn-currency"));

	function setCurrency(code) {
	    fetch("/currency/set", {
	        method: "POST",
	        headers: { 'Content-Type': 'application/json' },
	        body: JSON.stringify({ currency: code })
	    }).then(res => {
	        if (res.ok) {
	            //window.alert("Currency set to " + code);
	            console.log("Currency set to " + code);
	            location.reload(); // optional
	        } else {
	            alert("Invalid currency code");
	        }
	    });
	}
	
	function getCurrentCurrency() {
	    fetch("/currency/get")
	        .then(res => res.json())
	        .then(data => {
	            console.log("Current currency:", data);
	        });
	}	
	
	function setUpButtons(buttons) {
		buttons.forEach((btn) => {
			btn.addEventListener("click", ()=>{
				//console.log("test: "+btn.innerText);
				checkSelectedCurrency(btn);
				setCurrency(btn.dataset.code);
			})
		});
	}
	
	function checkSelectedCurrency(btn) {
		if (selectedCurrency === btn.dataset.code) {
			//	window.alert(selectedCurrency+" === "+btn.dataset.code);
			btn.classList.add("bg-light", "text-dark" , "font-weight-bold");
		}
	}
	
	function init() {
		btnCurrencyList.forEach((btn) => {
			checkSelectedCurrency(btn);
		});
		setUpButtons(btnCurrencyList);
	}
	
	init();
	
});


