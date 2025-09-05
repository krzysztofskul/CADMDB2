document.addEventListener('DOMContentLoaded', () => {

	let btnCurrencyList = Array.from(document.getElementsByClassName("btn-currency"));
	let divCurrencyValue = Array.from(document.getElementsByClassName("currency"));
	let form = document.getElementById('form');
	//let selectedCurrency = selectedCurrency; //session value (from header.html)
	//let exchangeRateEUR //session value (from header.html)
	//let exchangeRateUSD //session value (from header.html)

	function setUpForm() {
		form.addEventListener('submit', function(event) {
			event.preventDefault();
			console.log("*** TEST: submit has been clicked! ***")
			divCurrencyValue.forEach((element)=> {
				if (selectedCurrency === 'EUR') {
				element.value = (element.value / exchangeRateEUR).toFixed(2);					
				}
				if (selectedCurrency === 'USD') {
					element.value = (element.value / exchangeRateUSD).toFixed(2);						
				}

			});
			form.submit(); 
		});		
	}

	
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
	
	/* 
	textValue: text of the currency in text format
	currency: EUR or USD
	*/
	function exchangeCurrencyValue(textValue, currency) {
		const rawText = textValue.trim();
	    // Replace space (thousands separator) with empty string
	    // Replace comma (decimal separator) with dot
	    const normalized = rawText.replace(/\s/g, '').replace(',', '.');
	    const valuePLN = parseFloat(normalized);	
    	const valueEUR = (valuePLN * exchangeRateEUR).toFixed(2);
        const valueUSD = (valuePLN * exchangeRateUSD).toFixed(2);
		
		if (currency === 'EUR') {
			return valueEUR;
		}
		if (currency === 'USD') {
			return valueUSD;
		}
		return valuePLN;
	}
	
	function checkSelectedCurrency(btn) {
		if (selectedCurrency === btn.dataset.code) {
			divCurrencyValue.forEach((element) => {
				//check and exchange currency for input fields
				if (element.classList.contains("form-control")) { 
			        if (selectedCurrency === 'EUR') {
						element.value = exchangeCurrencyValue(element.value, 'EUR');
					}
			        if (selectedCurrency === 'USD') {
						element.value = exchangeCurrencyValue(element.value, 'USD');
					}
				}
				//check and exchange currency for not input fields 
				else { 
			        if (selectedCurrency === 'EUR') {
						element.textContent = exchangeCurrencyValue(element.innerText, 'EUR') + ' EUR';
					}
			        if (selectedCurrency === 'USD') {
						element.textContent = exchangeCurrencyValue(element.innerText, 'USD') + ' USD';
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
		setUpForm();
	}
	
	init();
	
});


