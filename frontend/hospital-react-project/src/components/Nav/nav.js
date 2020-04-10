import React from 'react';
import logo from '../../logo.svg';
import { Link } from 'react-router-dom';


function Nav() {

    return (
       // <nav>
       //     <ul className="nav-links">
       //         <Link to='/'>
       //             <li>Home</li>
       //         </Link>
       //         <Link to='/specializations'>
       //             <li>Specialization</li>
       //         </Link>
       //
       //     </ul>
       // </nav>

        //the slidebar
        <div>
            <div className="sidebar">
                <Link className="active" to="/">Home</Link>
                <a href="/specializations">Specializations</a>
                <a href="#contact">Contact</a>
                <a href="#about">About</a>
            </div>
        </div>

    )

}

export default Nav;
