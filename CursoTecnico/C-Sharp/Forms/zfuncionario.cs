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
    public partial class zfuncionario : Form
    {
        public zfuncionario()
        {
            InitializeComponent();
        }

       

        private void btnCad_Click(object sender, EventArgs e)
        {
            int x;

            if (txtNivel.Text == "ADM")
            {
                x = 1;
            }
            else if (txtNivel.Text == "FUNCIONÁRIO")
            {
                x = 2;
            }
            else 
            {
                x = 3;
            }

            clIntegracao CI = new clIntegracao();
            CI.inserir_Fun(txtNome.Text, txtFun.Text, txtRG.Text, txtUser.Text, txtCPF.Text, txtSenha.Text, x);
            MessageBox.Show("Cadastro efetuado com sucesso!!!");
            txtNome.Clear();
            txtRG.Clear();
            txtSenha.Clear();
            txtUser.Clear();
            txtFun.Clear();
            txtCPF.Clear();
            txtNome.Focus();
            txtNivel.Text = "";
        }


        private void btnCon_Click_1(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlDataAdapter da = new SqlDataAdapter("SELECT nome_fun as NOME, username as USUÁRIO, nv_acesso as [NIVEL DE ACESSO], rg_fun as RG, cpf_fun as CPF, funcao_fun as FUNÇÃO FROM tb_fun", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExclu_Click_1(object sender, EventArgs e)
        {
            clIntegracao con = new clIntegracao();
            con.Excluir_Fun(dg.CurrentRow.Cells[4].Value.ToString()); 
        }

        private void btnVoltar_Click(object sender, EventArgs e)
        {
            Menu men = new Menu();
            this.Close();
            
        }

        private void btnCadd_Click(object sender, EventArgs e)
        {
            int x;

            if (txtNivel.Text == "ADM")
            {
                x = 1;
            }
            else if (txtNivel.Text == "FUNCIONÁRIO")
            {
                x = 2;
            }
            else 
            {
                x = 3;
            }

            clIntegracao CI = new clIntegracao();
            CI.inserir_Fun(txtNome.Text, txtFun.Text, txtRG.Text, txtUser.Text, txtCPF.Text, txtSenha.Text, x);
            MessageBox.Show("Cadastro efetuado com sucesso!");
            txtNome.Clear();
            txtRG.Clear();
            txtSenha.Clear();
            txtUser.Clear();
            txtFun.Clear();
            txtCPF.Clear();
            txtNome.Focus();
            txtNivel.Text = "";
        }

        private void btnLimpar_Click_1(object sender, EventArgs e)
        {
            txtNome.Clear();
            txtRG.Clear();
            txtSenha.Clear();
            txtUser.Clear();
            txtFun.Clear();
            txtCPF.Clear();
            txtNome.Focus();
            txtNivel.Text = "";
        }

        
    }
}
