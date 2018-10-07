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
    public partial class zfornecedor : Form
    {
        public zfornecedor()
        {
            InitializeComponent();
        }

        private void btnCad_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_Forn(txtNome.Text, txtNomeFan.Text, txtCNPJ.Text, txtCEP.Text, txtCid.Text, txtEst.Text, txtNum.Text, txtTel.Text, txtEmail.Text, txtLog.Text);
            MessageBox.Show("Cadastro efetuado com sucesso!!!");
            txtNome.Clear();
            txtNomeFan.Clear();
            txtLog.Clear();
            txtEmail.Clear();
            txtCNPJ.Clear();
            txtCid.Clear();
            txtCEP.Clear();
            txtNum.Clear();
            txtTel.Clear();
            txtEst.Text = " ";
            txtCNPJ.Focus();
        }


        private void btnCad_Click_1(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_Forn(txtNome.Text, txtNomeFan.Text, txtCNPJ.Text, txtCEP.Text, txtCid.Text, txtEst.Text, txtNum.Text, txtTel.Text, txtEmail.Text, txtLog.Text);
            MessageBox.Show("Cadastro efetuado com sucesso!");
            txtNome.Clear();
            txtNomeFan.Clear();
            txtLog.Clear();
            txtEmail.Clear();
            txtCNPJ.Clear();
            txtCid.Clear();
            txtCEP.Clear();
            txtNum.Clear();
            txtTel.Clear();
            txtEst.Text = " ";
            txtCNPJ.Focus();
        }

        private void btnLimpar_Click_1(object sender, EventArgs e)
        {
            txtNome.Clear();
            txtNomeFan.Clear();
            txtLog.Clear();
            txtEmail.Clear();
            txtCNPJ.Clear();
            txtCid.Clear();
            txtCEP.Clear();
            txtNum.Clear();
            txtTel.Clear();
            txtEst.Text = " ";
            txtCNPJ.Focus();
        }

        

        private void btnCon_Click(object sender, EventArgs e)
        {
           clConexao con = new clConexao();
           SqlDataAdapter da = new SqlDataAdapter("SELECT nome_forn as NOME, nomefantas_forn as [NOME FANTASIA], cnpj_forn as CNPJ, cep_forn as CEP, cid_forn as CIDADE, est_forn as ESTADO, num_forn as NÚMERO, tel_forn as TELEFONE, email_forn as EMAIL, logra_forn as LOGRADOURO From tb_forn", con.conectarBD());
           DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExclu_Click_1(object sender, EventArgs e)
        {
            clIntegracao con = new clIntegracao();
            con.Excluir_Forn(dg.CurrentRow.Cells[2].Value.ToString());
        }

       
    }
}
