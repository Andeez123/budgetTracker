import logo from './logo.svg';
import './App.css';
import Header from './Components/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle"
import { Route, Router, Routes } from 'react-router-dom';
import Login from './Components/Login';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Header></Header>
        
        <Routes>
          <Route path='/' element={<Login></Login>}></Route>
        </Routes>
      </header>
    </div>
  );
}

export default App;
