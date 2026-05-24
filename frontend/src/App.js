import logo from './logo.svg';
import './App.css';
import Header from './Components/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle"
import { Route, Router, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Register from './Components/Register';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Header></Header>
        
        <Routes>
          <Route path='/' element={<Login></Login>}></Route>
          <Route path='/register' element={<Register></Register>}></Route>
        </Routes>
      </header>
    </div>
  );
}

export default App;
