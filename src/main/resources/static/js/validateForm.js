function validateForm() {
  const file = document.forms["file-form"]["file"].value;
  if (file === "") {
    alert("No file selected!");
    return false;
  }
}