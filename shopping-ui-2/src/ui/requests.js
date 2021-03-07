const baseUrl = "http://localhost:12000"

export const getProducts = async () => {
    try {
        let response = await fetch(
            `${baseUrl}/get-products`
        );
        let json = await response.json();
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const submitPurchaseRequest = async (purchaseRequest) => {
    try {
        let response = await fetch(
            `${baseUrl}/calculate-net-amount`
            ,{
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(purchaseRequest)
            });
        let json = await response.json();
        return json;
    } catch (error) {
        console.error(error);
    }
};
