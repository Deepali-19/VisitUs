package com.example.visitus

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.firestore

class firebaseActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore
        var collection = "Users"


        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_activty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db.collection(collectionName).addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            for (snapshot in snapshot!!.documentChanges ){
                val userModel = convertObject(snapshot.document)

                when (snapshot.type){
                    DocumentChange.Type.ADDED -> {
                        userModel?.let { categoriesList.add(it) }
                        Log.e("", "userModelList ${categoriesList.size}")
                        Log.e("", "userModelList ${categoriesList}")
                    }
                    DocumentChange.Type.MODIFIED -> {
                        userModel?.let {
                            val index = getIndex(UserModel)
                            if (index != -1)
                                categoriesList.set(index, it)

                        }
                    }
                    DocumentChange.Type.REMOVED -> {
                        userModel?.let {
                            val index = getIndex(UserModel)
                            if (index != -1)
                                categoriesList.removeAt(index)
                        }
                    }
                }
            }
            categoriesAdapter.notifyDataSetChanged()
        }
        categoriesListAdapter = CategoriesListAdapter(this, categoriesList, this)
        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)
        binding.recyclerCategory.adapter = categoriesListAdapter

        binding.fabAdd.setOnClickListener{
            try {

                db.collection(collectionName).add(CategoriesListModel(categoryName = "New Category"))
                    .addOnSuccessListener {
                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()

                    }
            }catch (e:Exception){
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun convertObject(snapshot: QueryDocumentSnapshot): CategoriesListModel? {
        val categoriesModel:CategoriesListModel? =
            snapshot.toObject(CategoriesListModel::class.java)
        categoriesModel?.categoryId = snapshot.id ?: ""
        return categoriesModel
    }
    fun getIndex(categoriesModel: CategoriesListModel): Int {
        var index = -1
        index = categoriesList.indexOfFirst { element ->
            element.categoryId?.equals(categoriesModel.categoryId) == true
        }
        return index
    }

    override fun delete(position: Int) {
        categoriesList[position].categoryId?.let {
            db.collection(collectionName).document(it).delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()

                }
    }
}
    override fun update(position: Int) {
        categoriesList[position].categoryId?.let {
            db.collection(collectionName).document(it)
                .set(CategoriesListModel(categoryName = "Document Updated"))
                .addOnSuccessListener {
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Update Failed ", Toast.LENGTH_SHORT).show()

                }

        }
    }


}