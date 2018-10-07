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
    public partial class frmConItemOrc : Form
    {
        public frmConItemOrc()
        {
            InitializeComponent();
        }
 

        private void dg_DoubleClick(object sender, EventArgs e)
        {
            txtCodOrc.Text = dg.CurrentRow.Cells[1].Value.ToString();
            txtCodProd.Text = dg.CurrentRow.Cells[0].Value.ToString();
            txtPrecoUnit.Text = dg.CurrentRow.Cells[3].Value.ToString();
            txtQuant.Text = dg.CurrentRow.Cells[2].Value.ToString();
            txtPrecoTotal.Text = dg.CurrentRow.Cells[4].Value.ToString();
        }

        private void btnExcluir_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_itemorc where cod_itemorc=@cod", con.conectarBD());
            cmd.Parameters.Add("@cod", SqlDbType.VarChar).Value = dg.CurrentRow.Cells[5].Value.ToString();
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }

        private void btnCon_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlCommand sql = new SqlCommand("SELECT tb_prod.nome_prod as [NOME DO PRODUTO],tb_itemorc.codorc_itemorc as [CÓDIGO DO ORÇAMENTO],tb_itemorc.qtde_itemorc as QUANTIDADE,tb_itemorc.preco_itemorc as PREÇO,tb_itemorc.precototal_itemorc as [PREÇO TOTAL],tb_itemorc.cod_itemorc as CÓDIGO FROM tb_itemorc,tb_prod WHERE codorc_itemorc=@cod and cod_prod=codprod_itemorc", con.conectarBD());
            sql.Parameters.Add("@cod", SqlDbType.VarChar).Value = txtCodOrc.Text;
            sql.ExecuteNonQuery();

            SqlDataAdapter da = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

       
    }
}
