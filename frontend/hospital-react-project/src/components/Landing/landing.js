import React from "react";
import logo from '../../img/logo5.png';
import patientLogin from '../../img/patient.png';
import adminLogin from '../../img/admin.png';
import doctorLogin from '../../img/doctor.jpg';
import img1 from '../../img/group4.jpg';
import img2 from '../../img/group3.jpg';
import img3 from '../../img/img3.jpg';

const Landing = (props) =>{
    return(
        <div>
            <nav className="navbar navbar-light navbar-expand-md fixed-top bg-light">
                <div className="container"><a className="navbar-brand" href="#logo5.png"><img id="logo"
                                                                                              src={logo}/><strong>Angelwood
                    Hospital&nbsp;</strong>
                    <button className="navbar-toggler" data-toggle="collapse"><span className="sr-only">Toggle navigation</span><span
                        className="navbar-toggler-icon"></span></button></a>
                    <div
                        className="collapse navbar-collapse" id="navcol-1">
                        <ul className="nav navbar-nav ml-auto">
                            <li className="nav-item" role="presentation"><a className="nav-link active" href="#">About
                                us</a></li>
                            <li className="nav-item" role="presentation"><a className="nav-link" href="#">Services</a>
                            </li>
                            <li className="nav-item" role="presentation"><a className="nav-link" href="#">Contact</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div id="promo">
                <h1 className="text-black-50 main-heder"><strong>We are here for you. For life</strong></h1>
                <div className="jumbotron">
                    <h1>How May We Help You?<br/></h1>
                    <p>
                        <button className="btn btn-info bg-info" type="button"><i
                            className="icon ion-person d-xl-flex justify-content-xl-center align-items-xl-start"></i>Find
                            a Doctor
                        </button>
                        <button className="btn btn-info bg-info" type="button"><i
                            className="icon ion-android-calendar d-xl-flex justify-content-xl-center"></i>Make an
                            appointment
                        </button>
                        <button
                            className="btn btn-info bg-info" type="button"><i
                            className="icon ion-ios-telephone d-xl-flex justify-content-xl-center"></i>Contact us
                        </button>
                    </p>
                </div>
            </div>
            <div>
                <div className="container site-login" id="login">
                    <div className="row">
                        <div className="col loginCol">
                            <div className="media"><img className="ml-auto mr-3 loginimg" src={patientLogin}/>
                                <div className="media-body">
                                    <h5><strong>&nbsp; &nbsp;Patients</strong></h5>
                                    <p>
                                      <a href="/signUpPatient"><button className="btn btn-primary" type="button" >Click here</button></a>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col loginCol">
                            <div className="media"><img className="mr-3 loginimg" src={adminLogin}/>
                                <div className="media-body">
                                    <h5><strong>Admin Login</strong></h5>
                                    <p>
                                        <a href="/login"><button className="btn btn-primary" type="button">Click here</button></a>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col loginCol">
                            <div className="media"><img className="mr-3 loginimg" src={doctorLogin}/>
                                <div className="media-body">
                                    <h5><strong>Doctor Login</strong></h5>
                                    <p>
                                        <a href="/signUpDoctor"><button className="btn btn-primary" type="button">Click here</button></a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container site-section" id="welcome">
                <h1><strong>Welcome to Angelwood Hospital Centar</strong></h1>
                <p>Angelwood Hospital Centar is dedicated to providing the most advanced health care to the communities
                    and patients we serve. The Angelwood Hospital Center and administrative staff, medical and science
                    faculty, and medical students come together
                    every day with a shared commitment to superior quality, academic excellence, scientific discovery
                    and patient safety.</p>
            </div>
            <div>
                <div className="container site-section" id="why">
                    <h1>Why Choose<strong>&nbsp;</strong>Us?</h1>
                    <div className="row">
                        <div className="col-md-4 item"><i className="icon ion-medkit"></i>
                            <h2><strong>More experience</strong><br/></h2>
                            <p>Every year, more than a million people come to Angelwood Hospital Center for care. Our
                                highly specialized experts are deeply experienced in treating rare and complex
                                conditions.</p>
                        </div>
                        <div className="col-md-4 item"><i className="icon ion-happy-outline"></i>
                            <h2><strong>The right answers</strong><br/></h2>
                            <p>Getting effective treatment depends on identifying the right problem. In a recent study,
                                88 percent of patients who came to Angelwood Hospital Center for a second opinion
                                received a new or refined diagnosis.</p>
                        </div>
                        <div className="col-md-4 item"><i className="icon ion-ios-heart"></i>
                            <h2><strong>Seamless care</strong><br/></h2>
                            <p>At Angelwood Hospital Center&nbsp;, every aspect of your care is coordinated and teams of
                                experts work together to provide exactly the care you need. What might take months
                                elsewhere can often be done in days here.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container site-section" id="gallery">
                <h1>Gallery</h1>
                <div className="row">
                    <div className="col-md-4"><a data-lightbox="photos" href={img1}
                                                 target="_blank"><img className="img-fluid img-responsive"
                                                                      src={img1}/></a></div>
                    <div className="col-md-4"><a data-lightbox="photos" href={img2}
                                                 target="_blank"><img className="img-fluid" src={img2}/></a>
                    </div>
                    <div className="col-md-4"><a data-lightbox="photos" href={img3} target="_blank"><img
                        className="img-fluid" src={img3}/></a></div>
                </div>
            </div>
            <footer>
                <div className="container">
                    <div className="row">
                        <div className="col-sm-6 col-md-4 footer-navigation">
                            <h3><a href="#">Angelwood Hospital Center</a></h3>
                            <p className="links"><a href="#">Home</a><strong> · </strong><a
                                href="#">Doctors</a><strong> · </strong><a
                                href="#">Appointment</a><strong>&nbsp;</strong></p>
                            <p className="company-name">Angelwood Hospital Center © 2020</p>
                        </div>
                        <div className="col-sm-6 col-md-4 footer-contacts">
                            <div><span className="fa fa-map-marker footer-contacts-icon"> </span>
                                <p><span className="new-line-span">21 Revolution Street</span> Paris, France</p>
                            </div>
                            <div><i className="fa fa-phone footer-contacts-icon"></i>
                                <p className="footer-center-info email text-left"> +1 555 123456</p>
                            </div>
                            <div><i className="fa fa-envelope footer-contacts-icon"></i>
                                <p><a href="#" target="_blank">support@ahcenter.com</a></p>
                            </div>
                        </div>
                        <div className="clearfix"></div>
                        <div className="col-md-4 footer-about">
                            <h4>About the company</h4>
                            <p><strong>Hospital accredited according to American standards for patient safety from
                                medical error and hospital-acquired infection</strong></p>
                            <div className="social-links social-icons"><a href="#"><i
                                className="fa fa-facebook"></i></a><a href="#"><i className="fa fa-twitter"></i></a><a
                                href="#"><i className="fa fa-linkedin"></i></a><a href="#"><i
                                className="fa fa-github"></i></a></div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    );
};

export default Landing;