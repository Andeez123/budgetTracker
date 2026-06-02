import logo from './logo.svg';
import './App.css';
import Header from './Components/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle"
import { Route, Router, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Register from './Components/Register';
import Transactions from './Components/Transactions';
import { PrimeReactProvider, PrimeReactContext } from 'primereact/api';     
import "primereact/resources/themes/lara-dark-blue/theme.css"; // theme - swap for others
import "primereact/resources/primereact.min.css";               // core styles
import "primeicons/primeicons.css"; 

function App() {
  return (
    <PrimeReactProvider>
      <div className="App">
      <header className="App-header">
        <Header></Header>
        
        <Routes>
          <Route path='/' element={<Login></Login>}></Route>
          <Route path='/register' element={<Register></Register>}></Route>
          <Route path='/home' element={<Transactions></Transactions>}></Route>
        </Routes>
      </header>
    </div>
    </PrimeReactProvider>
  );
}

export default App;
