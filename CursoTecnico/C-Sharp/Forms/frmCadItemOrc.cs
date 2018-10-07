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
    public partial class frmCadItemOrc : Form
    {
        public frmCadItemOrc()
        {
            InitializeComponent();
        }


        private void btnCad_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_ItemOrc(dg.CurrentRow.Cells[0].Value.ToString(), lblCod.Text, txtQuant.Text, txtPrecoUnit.Text, txtPrecoTotal.Text);
            txtPrecoTotal.Text = ((double.Parse(txtPrecoUnit.Text)) * (double.Parse(txtQuant.Text))).ToString();
            MessageBox.Show("Adicionado com sucesso"); 

            
             
        }

  
    }
}
