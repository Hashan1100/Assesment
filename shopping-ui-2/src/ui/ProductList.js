import {useEffect, useState} from "react";
import {getProducts} from "./requests";

const productTableWithPrize = [
    {
        type: "name",
        name: "Name",
        sublist: false
    },
    {
        type: "cartoonSize",
        name: "Cartoon Size",
        sublist: false
    },
    {
        type: "cartoonPrize",
        name: "Cartoon Prize",
        sublist: false
    },
    {
        type: "unitPrizes",
        name: "Unit Prizes",
        sublist: true
    }
]

function ProductList() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        getProducts().then(
            value => {
                setProducts(value)
            }
        )
    }, []);

    return <div>
        <h3>Product Prize List</h3>
        <table>
            {
                productTableWithPrize.map(value =>
                    <th>{value.name}</th>
                )
            }
            {
                products.map(product => {
                    return <tr>
                        {
                            productTableWithPrize.map(col => {
                                if (col.sublist) {
                                    return <UnitPrizes
                                        col={col}
                                        product={product}/>
                                } else {
                                    return <td>{product[col.type]}</td>
                                }

                            })
                        }
                    </tr>
                })
            }
        </table>
    </div>
}

export function UnitPrizes({product, col}) {
    return <td>
        <ul>
            {
                product[col.type].map(unitPrize => {
                    return <li>
                        {`Units : ${unitPrize.units} -> Total Prize: ${unitPrize.prize}`}
                    </li>
                })
            }
        </ul>
    </td>;
}

export default ProductList;