using DataAccess.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TransitSoftBusiness.BO;
using TransitSoftBusiness.BOI;

namespace TransitSoftWA
{
    /* Escriba su codigo y nombre completo
    Codigo PUCP:
    Nombre Completo:
    */
    public partial class AsignarVehiculosAConductor : System.Web.UI.Page
    {
        private Propietario propietario;
        private IPropietarioBO propietarioBO;
        private IVehiculoBO vehiculoBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            vehiculoBO = new VehiculoBOImpl();

            String accion = Request.QueryString["accion"];
            if (accion == null)
            {
                lblTitulo.Text = "Asignar Vehículos a Propietario";
                propietarioBO = new PropietarioBOImpl();
                ddlPropietarios.DataSource = propietarioBO.listarPropietariosConVehiculosAsignados(); //"Sin" no está en el ensamblado
                
                ddlPropietarios.DataBind();
                for(Propietario p: )
                ddlPropietarios.Items.Insert(0, new ListItem(propietario.DNINombreCompleto, propietario.Id.ToString()));


                ddlPropietarios.DataTextField = propietario.DNINombreCompleto;
                ddlPropietarios.DataValueField = propietario.Id.ToString();
            }
            else if(accion == "visualizar")
            {
                propietario = (Propietario)Session["propietario"];
                gvVehiculos.DataSource = vehiculoBO.listarVehiculosPorIDPropietario(propietario.Id);
                gvVehiculos.DataBind();
                ddlPropietarios.Items.Insert(0, new ListItem(propietario.DNINombreCompleto, propietario.Id.ToString()));
                ddlPropietarios.SelectedIndex = 0;
                lblTitulo.Text = "Ver Vehículos del Propietario";
                ddlPropietarios.Enabled = false;
                ddlVehiculos.Enabled = false;
                lbGuardar.Visible = false;
                lbAgregarVehiculo.Visible = false;
                gvVehiculos.Enabled = false;
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarVehiculosDePropietario.aspx");
        }

        protected void ddlPropietarios_SelectedIndexChanged(object sender, EventArgs e)
        {
            ddlPropietarios.Items

        }
    }
}