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
    public partial class frmConItemSaida : Form
    {
        public frmConItemSaida()
        {
            InitializeComponent();
        }

        private void dg_DoubleClick(object sender, EventArgs e)
        {
           txtCodProd.Text = dg.CurrentRow.Cells[3].Value.ToString();
           txtCodSaida.Text = dg.CurrentRow.Cells[0].Value.ToString();
           txtQuant.Text = dg.CurrentRow.Cells[2].Value.ToString();

        }

        private void btnCad_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlCommand sql = new SqlCommand("SELECT tb_prod.nome_prod as NOME,tb_itemsaida.codsd_itemsaida as [CÓDIGO DE ITEM SAÍDA],tb_itemsaida.qtde_itemsaida as QUANTIDADE,tb_itemsaida.cod_itemsaida as CÓDIGO FROM tb_prod,tb_itemsaida WHERE codsd_itemsaida = @cod and cod_prod = codprod_itemsaida", con.conectarBD());
            sql.Parameters.Add("@cod", SqlDbType.VarChar).Value = lblCod.Text;
            sql.ExecuteNonQuery();

            SqlDataAdapter da = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExc_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_itemsaida where cod_itemsaida=@cod", con.conectarBD());
            cmd.Parameters.Add("@cod", SqlDbType.VarChar).Value = dg.CurrentRow.Cells[3].Value.ToString();
            i = cmd.ExecuteNonQuery();
            if (i > 0)
            {
                MessageBox.Show("Exclusão efetuada com sucesso ! ");
                //CÓDIGO PARA CONSULTAR APÓS EXCLUSÃO
                SqlCommand sql = new SqlCommand("SELECT tb_prod.nome_prod as NOME,tb_itemsaida.codsd_itemsaida as [CÓDIGO DE ITEM SAÍDA],tb_itemsaida.qtde_itemsaida as QUANTIDADE,tb_itemsaida.cod_itemsaida as CÓDIGO FROM tb_prod,tb_itemsaida WHERE codsd_itemsaida = @cod and cod_prod = codprod_itemsaida", con.conectarBD());
                sql.Parameters.Add("@cod", SqlDbType.VarChar).Value = lblCod.Text;
                sql.ExecuteNonQuery();

                SqlDataAdapter da = new SqlDataAdapter(sql);
                DataSet ds = new DataSet();
                da.Fill(ds);
                dg.DataSource = ds.Tables[0];
            }
            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }

        
    }
}
