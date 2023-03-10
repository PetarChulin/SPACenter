// function calculate() {
//     let itemId = [[${sapropelOrders}]];
//     for (const itemIdKey in itemId) {
//         let qty = document.getElementById('quantity' + itemId[itemIdKey].id).value;
//         let temp = document.getElementById('price' + itemId[itemIdKey].id).textContent;
//         const regexp = /-?(\d+)(\.\d+)?/;
//         let result = temp.match(regexp)[0];
//         let price = parseFloat(result);
//         document.getElementById('total' + itemId[itemIdKey].id).innerHTML = (price * parseFloat(qty)).toString();
//     }
// }
//
// setTimeout(() => {
//     calculate()
// }, 100);
