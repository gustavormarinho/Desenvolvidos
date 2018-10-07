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
    public partial class Menu : Form
    {
        public Menu()
        {
            InitializeComponent();
        }

        
        private void btnCadIO_Click(object sender, EventArgs e)
        {
            
            frmCadItemOrc CadIO = new frmCadItemOrc();
            CIO.Show();
        }

        private void btnCadIP_Click(object sender, EventArgs e)
        {
            frmCadItemPed CadIP = new frmCadItemPed();
            CIP.Show();
        }

        private void btnCadIS_Click(object sender, EventArgs e)
        {
            fmrCadItemSaida CadIS = new fmrCadItemSaida();
            CIS.Show();
        }

        private void btnConIO_Click(object sender, EventArgs e)
        {
            frmConItemOrc ConIO = new frmConItemOrc();
            CI.Show();
        }

        private void btnConIP_Click(object sender, EventArgs e)
        {
            frmConItemPed ConIP = new frmConItemPed();
            CI.Show();
        }

        private void btnConIS_Click(object sender, EventArgs e)
        {
            frmConItemSaida ConIS = new frmConItemSaida();
            CI.Show();
        }

        private void Menu_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult resp;
            resp = MessageBox.Show("Deseja sair do programa", "Sair do programa", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
            if (resp != System.Windows.Forms.DialogResult.Yes)
                e.Cancel = true;
            else
            {
                zlogin lg = new zlogin();
                lg.Close();
            }
        }
        private void lblOrcamento_Click(object sender, EventArgs e)
        {
            zorcamento orc = new zorcamento();
            orc.Show();
        }

        private void lblFornecedor_Click(object sender, EventArgs e)
        {
            zfornecedor forn = new zfornecedor();
            forn.Show();
        }

        private void Menu_Load(object sender, EventArgs e)
        {
           

            if (zlogin.nvacesso == 3)
            {
                lblFornecedor.Visible = false;
                lblOrcamento.Visible = false;
                lblPedido.Visible = false;
                lblProduto.Visible = false;
                lblSaida.Visible = false;
                lblFuncionario.Visible = false;
                
            }
            else if (zlogin.nvacesso == 2)
            {
                lblFornecedor.Visible = true;
                lblOrcamento.Visible = true;
                lblPedido.Visible = true;
                lblProduto.Visible = true;
                lblSaida.Visible = true;
                lblFuncionario.Visible = false;
            }
            else if (zlogin.nvacesso == 1)
            {
                lblFornecedor.Visible = true;
                lblOrcamento.Visible = true;
                lblPedido.Visible = true;
                lblProduto.Visible = true;
                lblSaida.Visible = true;
                lblFuncionario.Visible = true;
            }


            lblNome.Text =zlogin.nome;
            



        }

        private void logoffToolStripMenuItem_Click(object sender, EventArgs e)
        {
            zlogin lo = new zlogin();
            this.Close();
            lo.Show();
            zlogin.nvacesso = 0;

        }

     

        private void lblFuncionario_Click(object sender, EventArgs e)
        {
            zfuncionario fun = new zfuncionario();
            fun.Show();
        }

        private void lblFornecedor_Click(object sender, EventArgs e)
        {
            zfornecedor forn = new zfornecedor();
            forn.Show();
        }

        private void lblOrcamento_Click(object sender, EventArgs e)
        {
            zorcamento orc = new zorcamento();
            orc.Show();
        }

        private void btnLog_Click(object sender, EventArgs e)
        {
            zlogin log = new zlogin();
            this.Close();
            log.Show();
        }

        private void lblSaida_Click(object sender, EventArgs e)
        {
            zsaida sd = new zsaida();
            sd.Show();
        }

        private void lblProduto_Click(object sender, EventArgs e)
        {
            zproduto prod = new zproduto();
            prod.Show();
        }

        private void lblPedido_Click(object sender, EventArgs e)
        {
            zpedido ped = new zpedido();
            ped.Show();
        }

        private void btnConectar_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();
            SqlCommand comm = new SqlCommand ("Select count(*) From tb_fun where username=@usuario", con.conectarBD());
            comm.Parameters.Add("@usuario", SqlDbType.VarChar).Value = lblHor.Text;
        }

        private void timerRelogio_Tick(object sender, EventArgs e)
        {
            lblHor.Text = DateTime.Now.ToShortTimeString();
            label1.Text = DateTime.Now.ToShortDateString();
        }
        
    }
}
