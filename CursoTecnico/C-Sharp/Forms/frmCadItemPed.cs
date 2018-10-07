using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace frm_CadFun
{
    public partial class frmCadItemPed : Form
    {
        public frmCadItemPed()
        {
            InitializeComponent();
        }

        private void btnCad_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_ItemPed(dg.CurrentRow.Cells[0].Value.ToString(), lblCodPed.Text, txtQntd.Text, txtValorUnit.Text, txtValorTotal.Text);
            MessageBox.Show("Cadastro efetuado com sucesso");
        }

       
    }
}
