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
    public partial class zsaida : Form
    {
        public zsaida()
        {
            InitializeComponent();
        }
        clIntegracao CI = new clIntegracao();

        private void btnCad_Click(object sender, EventArgs e)
        {
            if (txtSolic.Text == "")
            {
                MessageBox.Show("Digite o nome do solicitante");
            }
            else
            {
                try
                {
                    CI.inserir_Saida(txtCPF.Text, txtSolic.Text, txtData.Text);
                    MessageBox.Show("Saída de produtos iniciada! Escolha os Produtos.");
                    CI.trazcod_Saida();
                    lblCod.Text = CI.chave;
                    fmrCadItemSaida frm = new fmrCadItemSaida();
                    frm.Show();
                }
                catch
                {
                    MessageBox.Show("CPF Inválido.");
                }
            }
        }

        

        private void btnConsu_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlDataAdapter da = new SqlDataAdapter("SELECT cod_sd as CÓDIGO, cpf_sd as CPF, solic_sd as SOLICITANTE, data_sd as DATA  FROM tb_sd", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnExclu_Click(object sender, EventArgs e)
        {
            CI.Excluir_Sd(dg.CurrentRow.Cells[0].Value.ToString());
            clConexao con = new clConexao(); //CÓDIGO PARA REALIZAR OUTRA CONSULTA APÓS EXCLUSÃO
            SqlDataAdapter da = new SqlDataAdapter("SELECT cod_sd as CÓDIGO, cpf_sd as CPF, solic_sd as SOLICITANTE, data_sd as DATA  FROM tb_sd", con.conectarBD());
            DataSet ds = new DataSet();
            da.Fill(ds);
            dg.DataSource = ds.Tables[0];
        }

        private void btnDet_Click(object sender, EventArgs e)
        {
            zorcamento.ver = int.Parse(dg.CurrentRow.Cells[0].Value.ToString());
            frmConItemSaida cis = new frmConItemSaida();
            cis.Show();
        }

       

    }
}
