async function calculate() {
    let itemId = [[${allOrders}]];
    for (const itemIdKey in itemId) {
        let temp = document.getElementById('price' + itemId[itemIdKey].type + itemId[itemIdKey].id).textContent;
        let qty = await document.getElementById('quantity' + itemId[itemIdKey].type + itemId[itemIdKey].id).value;
        const regexp = /-?(\d+)(\.\d+)?/;
        let result = temp.match(regexp)[0];
        let price = parseFloat(result);
        document.getElementById('total' + itemId[itemIdKey].type + itemId[itemIdKey].id).innerHTML = (price * parseFloat(qty)).toFixed(2);
    }
}

setTimeout(() => {
    calculate()
}, 100);
