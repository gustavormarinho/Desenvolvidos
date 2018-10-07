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
    public partial class zproduto : Form
    {
        public zproduto()
        {
            InitializeComponent();
        }

        private void btnNovoCad_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.trazcodprod();
            lblCod.Text = CI.chave;
            btnNovoCad.Enabled = false;
            btnCad.Enabled = true;
        }

        private void btnCad_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.inserir_Produto(txtNome.Text, txtTipo.Text, txtQuant.Text, txtDescri.Text, txtLocal.Text);
            MessageBox.Show("Cadastro efetuado com sucesso");
            btnNovoCad.Enabled = true;
            btnNovoCad.Focus();
            btnCad.Enabled = false;
            txtNome.Clear();
            txtLocal.Clear();
            txtDescri.Clear();
            txtQuant.Clear();
            txtTipo.Clear();
            txtNome.Focus();
        }

        private void btnConsu_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlDataAdapter da = new SqlDataAdapter("SELECT cod_prod as CÓDIGO, nome_prod as NOME, tipo_prod as TIPO, qtde_prod as [QUANTIDADE DE ESTOQUE], desc_prod as DESCRIÇÃO, local_prod as LOCALIZAÇÃO FROM tb_prod", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExclu_Click(object sender, EventArgs e)
        {
            clIntegracao CI = new clIntegracao();
            CI.Excluir_Prod(dg.CurrentRow.Cells[0].Value.ToString());
        }

        
    }
}
