package com.johncoimbra.lojavirtiual.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.FragmentProdutosBinding
import com.johncoimbra.lojavirtiual.model.Data
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import java.text.FieldPosition

class ProdutosFragment : Fragment() {

    private lateinit var mAdapter: GroupAdapter<ViewHolder>
    private var mFrangmentProdutos: FragmentProdutosBinding? = null
    private var mFirebaseStore = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_produtos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindingFrangmentProdutos = FragmentProdutosBinding.bind(view)
        mFrangmentProdutos = bindingFrangmentProdutos
        val recyclerViewProdutos = bindingFrangmentProdutos.recyclerProdutos

        mAdapter = GroupAdapter()
        recyclerViewProdutos.adapter = mAdapter
        mAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(context, "Item Clicado", Toast.LENGTH_SHORT).show()
        }
        getProducts()

    }

    private inner class productsItem(internal val mAdapterProdutos: Data) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.list_produtos

        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.findViewById<TextView>(R.id.text_nome_produto).text = mAdapterProdutos.nome
            viewHolder.itemView.findViewById<TextView>(R.id.text_preco_produto).text = mAdapterProdutos.preco
            var fotoProduto = viewHolder.itemView.findViewById<ImageView>(R.id.image_foto_produto)
            Picasso.get().load(mAdapterProdutos.uid).into(fotoProduto)
        }
    }

    private fun getProducts() {
        mFirebaseStore.collection("Produtos")
            .addSnapshotListener { snapshot, exeption ->
                exeption?.let {
                    return@addSnapshotListener
                }

                snapshot?.let {
                    for (doc in snapshot) {
                        val produtos = doc.toObject(Data::class.java)
                        mAdapter.add(productsItem(produtos))
                    }
                }
            }
    }
}