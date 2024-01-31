package co.com.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.tienda.dao.IDetalleOrdenDao;
import co.com.tienda.domain.DetalleOrden;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{

    @Autowired
    private IDetalleOrdenDao detalleOrdenDao;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        // TODO Auto-generated method stub
        return detalleOrdenDao.save(detalleOrden);
    }
    
}
