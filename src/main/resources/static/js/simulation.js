document.addEventListener('DOMContentLoaded', () => {
	
	let simulationId = document.getElementById("simulationId").dataset.simulationId;
	let input = document.getElementById("inputSimulationTime");
	let button = document.getElementById("btnSimulationTimeEdit");

	function changeButtonToSave() {
	  const button = document.getElementById("btnSimulationTimeEdit");
	  const input = document.getElementById("inputSimulationTime");
	
	  // Enable input field
	  input.disabled = false;
	
	  // Change button label
	  button.querySelector(".langPL").textContent = "ZAPISZ";
	  button.querySelector(".langEN").textContent = "save";
	
	  // Optional: change button style
	  button.classList.remove("btn-outline-primary");
	  button.classList.add("btn-success");
	
	  // Update mode
	  button.dataset.mode = "save";
	}

	function changeButtonToEdit() {
	  const button = document.getElementById("btnSimulationTimeEdit");
	  const input = document.getElementById("inputSimulationTime");
	
	  // Disable input field
	  input.disabled = true;
	
	  // Change button label
	  button.querySelector(".langPL").textContent = "ZMIEŃ";
	  button.querySelector(".langEN").textContent = "change";
	
	  // Optional: revert button style
	  button.classList.remove("btn-success");
	  button.classList.add("btn-outline-primary");
	
	  // Update mode
	  button.dataset.mode = "edit";
	}

	let doFetch = ()=> {fetch("/rest/simulations/"+simulationId, {
			method: "POST",
			body: JSON.stringify({
				id: simulationId,
				time: input.value
			}),
			headers: {
				"Content-type": "application/json; charset=UTF-8"
				}
		})
			.then(data => {
				//console.log("TEST:", data);
				window.location.reload();
			})
	}
	
	  button.addEventListener("click", (event) => {
	    event.preventDefault();
	    if (button.dataset.mode === "edit") {
	      changeButtonToSave();
	      button.dataset.mode = "save";
	    } else {
	      changeButtonToEdit();
	      button.dataset.mode = "edit";
	      doFetch();
	    }
	  });

	
});