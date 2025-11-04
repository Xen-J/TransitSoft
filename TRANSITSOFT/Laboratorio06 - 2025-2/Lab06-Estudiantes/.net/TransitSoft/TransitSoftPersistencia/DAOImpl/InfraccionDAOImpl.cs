using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Persistencia.DAO;
using PUCP.TransitSoft.Db;
using System;
using System.Collections.Generic;
using System.Data.Common;

namespace PUCP.TransitSoft.Persistencia.DAOImpl {
    public class InfraccionDAOImpl : BaseDAOImpl<Infraccion>, IInfraccionDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, Infraccion modelo)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoCrear(DbConnection conn, Infraccion modelo)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            throw new NotImplementedException();
        }

        protected override Infraccion MapearModelo(DbDataReader reader)
        {
            throw new NotImplementedException();
        }
    }
}
