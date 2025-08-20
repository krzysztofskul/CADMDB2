document.addEventListener('DOMContentLoaded', () => {

	let btnCurrencyList = Array.from(document.getElementsByClassName("btn-currency"));
	let divCurrencyValue = Array.from(document.getElementsByClassName("currency"));
	//let selectedCurrency = selectedCurrency; //session value (from header.html)
	//let exchangeRateEUR //session value (from header.html)
	//let exchangeRateUSD //session value (from header.html)

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
			//window.alert("TEST: "+selectedCurrency+" === "+btn.dataset.code);
			//console.log("TEST: actual currency: " + selectedCurrency);
			//console.log("TEST: exchangeRateEUR = " + exchangeRateEUR);
			//console.log("TEST: exchangeRateUSD = " + exchangeRateUSD);
			divCurrencyValue.forEach((element) => {
/*				console.log("---TEST---");
				console.log("---currency exchange---");
				console.log(element.innerText + " PLN");
				console.log(((parseFloat(element.innerText))*exchangeRateEUR).toFixed(2) + " EUR");
*/
    			const rawText = element.innerText.trim();
			    // Replace space (thousands separator) with empty string
			    // Replace comma (decimal separator) with dot
			    const normalized = rawText.replace(/\s/g, '').replace(',', '.');
			    const valuePLN = parseFloat(normalized);			
			    if (!isNaN(valuePLN)) {
			        const valueEUR = (valuePLN * exchangeRateEUR).toFixed(2);
			        const valueUSD = (valuePLN * exchangeRateUSD).toFixed(2);
			        //console.log(`${valuePLN} PLN`);
			        //console.log(`${valueEUR} EUR`);
			        if (selectedCurrency === 'EUR') {
						//set th:text = ${valueEUR}
						element.textContent = `${valueEUR} EUR`;
					}
			        if (selectedCurrency === 'USD') {
						//set th:text = ${valueUSD}
						element.textContent = `${valueUSD} USD`;
					}
			    }

			});
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


