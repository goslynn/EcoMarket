import { div } from 'framer-motion/client'
import React from 'react'
import icon from '../../assets/EcoMarket-logo.webp'
/*

*/
const CardCrud = ({ titulo, desc, icono }) => {
  return (
    <div className='card p-0 w-100'>
      <div className=" d-flex p-4">
        <img src={icono} alt={titulo} width={"200px"}/>
        <div className="card-body">
            <h5 className="card-title fs-2">{titulo}</h5>
            <p className="card-text">{desc}</p>
            <a href="#" className="btn btn-success">Gestionar </a>
        </div>
      </div>
    </div>
  )
}

export default CardCrud