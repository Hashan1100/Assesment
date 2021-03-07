import {useEffect, useState} from "react";
import {getProducts, submitPurchaseRequest} from "./requests";

const productTable = [
    {
        type: "name",
        name: "Name"
    },
    {
        type: "cartoonSize",
        name: "Cartoon Size"
    },
    {
        type: "cartoonPrize",
        name: "Cartoon Prize"
    }
]

function PurchaseView() {
    const [productList, setProductList] = useState([]);
    const [purchase, setPurchase] = useState([]);
    const [netAmount, setNetAmount] = useState([]);

    useEffect(() => {
        getProducts().then(
            value => {
                setProductList(value)
            }
        )
    }, []);

    useEffect(() => {
        const list = []
        productList.map(ele => list.push({
            productId: ele.id,
            amount: 0,
            buyCartoon: false
        }))
        setPurchase(list)
    }, [productList]);

    function setPurchaseResponse({id, amount}) {
        setPurchase(purchase.map(item => item.productId === id ? {
            productId: item.productId,
            amount: amount,
            buyCartoon: item.buyCartoon
        } : item))
    }

    function setSetCartoonPurchase({id, buyCartoon}) {
        setPurchase(purchase.map(item => item.productId === id ? {
            productId: item.productId,
            amount: item.amount,
            buyCartoon: buyCartoon
        } : item))
    }


    function submit() {
        submitPurchaseRequest(purchase)
            .then(value => {
                setNetAmount(value.netAmount)
            })
            .catch(reason => {
                console.error(reason)
            })
    }

    return <div>
        <h3>Purchase</h3>
        <table>
            {
                productTable.map(value =>
                    <th>{value.name}</th>
                )
            }
            <th>Cartoon Purchase</th>
            <th>Amount</th>
            {
                productList.map(value => {
                    return <tr>
                        {productTable.map(element => <td>{value[element.type]}</td>)}
                        <td>
                            <input type={"checkbox"} onChange={event => {
                            setSetCartoonPurchase({
                                id: value.id,
                                buyCartoon: event.target.checked
                            })
                        }
                        }/></td>
                        <td><input type={"number"} onChange={event => {
                            const number = parseInt(event.target.value);
                            setPurchaseResponse({
                                id: value.id,
                                amount: number
                            })
                        }
                        }/></td>
                    </tr>
                })
            }
        </table>
        <button onClick={submit}>Submit</button>
        {netAmount &&
        <div>Net Amount: {netAmount}</div>
        }

    </div>

}

export default PurchaseView;

