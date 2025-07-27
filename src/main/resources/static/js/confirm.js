export function setupDeleteConfirmation() {
  const deleteButtons = document.querySelectorAll(".btn-outline-danger");

  deleteButtons.forEach(button => {
    button.addEventListener("click", event => {
      const confirmed = confirm("Czy na pewno chcesz usunąć...?\nAre you sure you want to delete...?");
      if (!confirmed) {
        event.preventDefault(); // Stop the action (like form submission or link navigation)
      }
    });
  });
};