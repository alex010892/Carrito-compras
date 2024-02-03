package co.com.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.tienda.dao.IRolDao;
import co.com.tienda.domain.Rol;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private IRolDao rolDao;

    @Override
    public Rol guardar(Rol rol) {
        // TODO Auto-generated method stub
        return rolDao.save(rol);
    }
    
}
