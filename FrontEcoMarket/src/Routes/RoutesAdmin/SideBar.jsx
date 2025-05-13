import React from 'react'
import menuSVG from '../../assets/menu.svg'
const SideBar = () => {
  return (
<div class="sidebar sidebar-narrow-unfoldable sidebar-dark border-end">
  <div class="sidebar-header border-bottom">
    <div class="sidebar-brand"><img src={menuSVG} width={"40px"}/></div>
  </div>
  <ul class="sidebar-nav">
    <li class="nav-title">Inventario</li>
    <li class="nav-item">
      <a class="nav-link" href="#">
        <i class="nav-icon cil-speedometer"></i> Familia
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">
        <i class="nav-icon cil-speedometer"></i> SubFamilia
      </a>
    </li>
    <li class="nav-item nav-group show">
      <a class="nav-link " href="#">
        <i class="nav-icon cil-puzzle"></i> Productos
      </a>
    </li>
          <ul class="nav-group-items">
        <li class="nav-item">
            <a href="" className='nav-title'>Administracion</a>
          <a class="nav-link" href="#">
            <span class="nav-icon"><span class="nav-icon-bullet"></span></span> Crear usuario
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <span class="nav-icon"><span class="nav-icon-bullet"></span></span> Nav dropdown item
          </a>
        </li>

        
      </ul>
    <li class="nav-item mt-auto">
      <a class="nav-link" href="https://coreui.io">
        <i class="nav-icon cil-cloud-download"></i>volver a cliente</a>
    </li>
  </ul>
</div>
  )
}

export default SideBar