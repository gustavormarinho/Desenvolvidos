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
    public partial class frmConItemPed : Form
    {
        public frmConItemPed()
        {
            InitializeComponent();
        }
        
        private void dg_DoubleClick(object sender, EventArgs e)
        {
            try
            {
                txt1.Text = dg.CurrentRow.Cells[1].Value.ToString();
                txtQntd.Text = dg.CurrentRow.Cells[2].Value.ToString();
                txtValorTotal.Text = dg.CurrentRow.Cells[4].Value.ToString();
                txtCodProd.Text = dg.CurrentRow.Cells[0].Value.ToString();
                txtValorUnit.Text = dg.CurrentRow.Cells[3].Value.ToString();
            }
            catch 
            {
                MessageBox.Show("Erro");
            }
        }

        private void btnCon_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlCommand sql = new SqlCommand("SELECT tb_prod.nome_prod as NOME,tb_itemped.codped_itemped as [CÓDIGO DO PEDIDO],tb_itemped.qtde_itemped as QUANTIDADE,tb_itemped.preco_itemped as PREÇO,tb_itemped.precototal_itemped as [PREÇO TOTAL], tb_itemped.cod_itemped as [CÓDIGO DO ITEM PEDIDO] FROM tb_itemped,tb_prod WHERE codped_itemped=@cod and cod_prod = codprod_itemped", con.conectarBD());
            sql.Parameters.Add("@cod", SqlDbType.VarChar).Value = txt1.Text;
            sql.ExecuteNonQuery();

            SqlDataAdapter da = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExluir_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_itemped where cod_itemped=@cod", con.conectarBD());
            cmd.Parameters.Add("@cod", SqlDbType.VarChar).Value = dg.CurrentRow.Cells[5].Value.ToString();
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");

        }

        private void frmConItemPed_Load(object sender, EventArgs e)
        {
            txt1.Text = zorcamento.ver.ToString();
        }

        
    }
}
