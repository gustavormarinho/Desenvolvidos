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
    public partial class zpedido : Form
    {
        public zpedido()
        {
            InitializeComponent();
        }

      
        private void btnNovoCad_Click_1(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.trazcodped();
            lblCodPed.Text = CI.chave;
            txtCPF.Focus();
            btnNovoCad.Enabled = false;
            btnCad.Enabled = true;
        }

        private void btnCad_Click_1(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_Ped(txtData.Text, txtCNPJ.Text, txtCPF.Text, txtValor.Text, txtDataFisc.Text, txtNumFis.Text, txtDataEntre.Text);
            MessageBox.Show("Cadastro efetuado com sucesso");
            btnNovoCad.Enabled = true;
            btnNovoCad.Focus();
            btnCad.Enabled = false;
            txtCNPJ.Clear();
            txtCPF.Clear();
            txtNumFis.Clear();
            txtValor.Clear();
            txtCPF.Focus();
            frmCadItemPed CIP = new frmCadItemPed();
            CIP.Show();
            
        }

        private void btnConsu_Click_1(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlDataAdapter da = new SqlDataAdapter("SELECT cod_ped as CÓDIGO, data_ped as DATA, cnpj_ped as CNPJ, cpf_ped as CPF, valortotal_ped as [VALOR TOTAL], datanf_ped as [DATA NOTA FÍSCAL], numnf_ped as [NÚMERO NOTA FÍSCAL], dataent_ped as [DATA DE ENTREGA] FROM tb_ped", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
            this.dg.Columns["VALOR TOTAL"].DefaultCellStyle.Format = "c";
        }

        private void btnExclu_Click_1(object sender, EventArgs e)
        {
            clIntegracao con = new clIntegracao();
            con.Excluir_Ped(dg.CurrentRow.Cells[0].Value.ToString()); 
        }

        private void btnDet_Click_1(object sender, EventArgs e)
        {
            zorcamento.ver = int.Parse(dg.CurrentRow.Cells[0].Value.ToString());
            frmConItemPed cip = new frmConItemPed();
            cip.Show();
        }

        
        
    }
}
