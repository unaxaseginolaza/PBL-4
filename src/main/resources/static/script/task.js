function ensureOnlyOneMaterialSelected(selectedElement, otherElementId) {
    const otherElement = document.getElementById(otherElementId);
    if (selectedElement.value !== "") {
        otherElement.selectedIndex = 0;
        otherElement.disabled = true;
    } else {
        otherElement.disabled = false;
    }
}