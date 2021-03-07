import logo from './logo.svg';
import './App.css';
import PurchaseView from "./ui/purchaseView";
import ProductList from "./ui/ProductList";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <ProductList/>
        <PurchaseView />
      </header>
    </div>
  );
}

export default App;
