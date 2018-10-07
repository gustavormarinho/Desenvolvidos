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

    public partial class fmrCadItemSaida : Form
    {
        
        public fmrCadItemSaida()
        {
            InitializeComponent();
        }

       
        private void btnCad_Click(object sender, EventArgs e)
        {
            if (lblCodProd.Text == "")
            {
                MessageBox.Show("Código do Produto Inválido. Clique duas vezes sobre um produto.");
            }
            else
            {

                clConexao con = new clConexao();
                clIntegracao CI = new clIntegracao();

                SqlCommand sql = new SqlCommand("select qtde_prod from tb_prod where cod_prod=@cod_item", con.conectarBD());
                sql.Parameters.Add("@cod_item", SqlDbType.VarChar).Value = lblCodProd.Text;
                string x = sql.ExecuteScalar().ToString();
                int qtdeatual = int.Parse(x);

                if (int.Parse(txtQuant.Text) > qtdeatual)
                    MessageBox.Show("Não foi possível retirar o produto. Quantidade desejada acima do estoque");

                else
                {
                    CI.inserir_ItemSaida(dg.CurrentRow.Cells[0].Value.ToString(), lblCodsd.Text, txtQuant.Text);

                    qtdeatual = qtdeatual - int.Parse(txtQuant.Text);

                    SqlCommand sql2 = new SqlCommand("update tb_prod set qtde_prod=@qtde where cod_prod=@cod_prod", con.conectarBD());
                    sql2.Parameters.Add("@qtde", SqlDbType.VarChar).Value = qtdeatual.ToString();
                    sql2.Parameters.Add("@cod_prod", SqlDbType.VarChar).Value = lblCodProd.Text;
                    sql2.ExecuteNonQuery();

                    MessageBox.Show("Produto adicionado à saída!");
                }
                clConexao conn = new clConexao();
                SqlDataAdapter da = new SqlDataAdapter("SELECT cod_prod as [CÓDIGO DO PRODUTO], nome_prod as [NOME DO PRODUTO], tipo_prod as TIPO, qtde_prod as QUANTIDADE, desc_prod as DESCRIÇÂO, local_prod as LOCALIZAÇÃO FROM tb_prod", conn.conectarBD());
                DataSet ds = new DataSet();
                da.Fill(ds);
                dg.DataSource = ds.Tables[0];
            }
        }

        

        private void dg_DoubleClick(object sender, EventArgs e)
        {
            lblCodProd.Text = dg.CurrentRow.Cells[0].Value.ToString();
        }

        private void fmrCadItemSaida_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult resp;
            resp = MessageBox.Show("Deseja voltar ao menu?", "Voltar ao menu", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
                if(resp != System.Windows.Forms.DialogResult.Yes)
                    e.Cancel = true;
        }


    }
}
