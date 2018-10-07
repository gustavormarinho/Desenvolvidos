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
    public partial class zorcamento : Form
    {

        public static int ver;

        public zorcamento()
        {
            InitializeComponent();
        }

        
        private void btnNovoCad_Click_1(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.trazcodorc();
            lblCodOrc.Text = CI.chave;
            btnNovoCad.Enabled = false;
            btnCad.Enabled = true;
            txtCNPJ.Clear();
            txtCPF.Clear();
            txtValorTotal.Clear();
            txtCNPJ.Focus();
        }

        private void btnCad_Click_1(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_Orc(txtData.Text, txtCPF.Text, txtCNPJ.Text, txtValorTotal.Text);
            MessageBox.Show("Cadastro efetuado com sucesso");
            btnNovoCad.Enabled = true;
            btnNovoCad.Focus();
            btnCad.Enabled = false;
            txtCNPJ.Clear();
            txtValorTotal.Clear();
            txtCNPJ.Focus();
            frmCadItemOrc CIO = new frmCadItemOrc();
            CIO.Show();
           
        }

        private void btnConsu_Click_1(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlDataAdapter da = new SqlDataAdapter("SELECT cod_orc as CÓDIGO, data_orc as DATA, cpf_orc as CPF, cnpj_orc as CNPJ, valortotal_orc as [VALOR TOTAL] FROM tb_orc", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
            this.dg.Columns["VALOR TOTAL"].DefaultCellStyle.Format = "c";
        }

        private void btnExclu_Click_1(object sender, EventArgs e)
        {
            clIntegracao con = new clIntegracao();
            con.Excluir_Orc(dg.CurrentRow.Cells[0].Value.ToString()); 
        }
         
      
        
        private void btnDet_Click_1(object sender, EventArgs e)
        {
        ver = int.Parse(dg.CurrentRow.Cells[0].Value.ToString());
            frmConItemOrc cio = new frmConItemOrc();
            cio.Show();
        }

     
    }


       
  
}
