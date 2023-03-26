function filter() {
    let input, filter, h5, i, txtValue;
    input = document.getElementById("searched");
    filter = input.value.toUpperCase();
    h5 = document.getElementsByTagName("h5");

    for (i = 0; i < h5.length; i++) {
        txtValue = h5[i].textContent || h5[i].innerHTML;
        let temp = h5[i].parentNode.parentNode;
        console.log(temp)
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            temp.style.display = "";
        } else {
            temp.style.display = "none";
        }
    }
}
