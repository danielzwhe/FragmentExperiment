package im.danielzwhe.app.fragmentexp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var supportFragmentManagerAddButton: MaterialButton
    private lateinit var supportFragmentManagerReplaceButton: MaterialButton
    private lateinit var addToBackStackToggle: SwitchMaterial
    private lateinit var setPrimaryNavigationFragmentToggle: SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        initView()
    }

    private fun findViews() {
        supportFragmentManagerAddButton = findViewById(R.id.supportFragmentManagerAddButton)
        supportFragmentManagerReplaceButton = findViewById(R.id.supportFragmentManagerReplaceButton)
        addToBackStackToggle = findViewById(R.id.addToBackStackToggle)
        setPrimaryNavigationFragmentToggle = findViewById(R.id.setPrimaryNavigationFragmentToggle)
    }

    private fun initView() {
        supportFragmentManagerAddButton.setOnClickListener {
            addByParentFragmentManager()
        }

        supportFragmentManagerReplaceButton.setOnClickListener {
            replaceByParentFragmentManager()
        }
    }

    private fun isAddToBackStack(): Boolean {
        return addToBackStackToggle.isChecked
    }

    private fun isSetPrimaryNavigationFragment(): Boolean {
        return setPrimaryNavigationFragmentToggle.isChecked
    }

    private fun addByParentFragmentManager() {
        val history =
            StringBuilder()
                .append("add via sfm, backStack: ${isAddToBackStack()}, setPrimary: ${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = HostFragment(history)
        val tag = fm::class.java.canonicalName
        supportFragmentManager.beginTransaction().apply {
            add(R.id.activityFragmentContainer, fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }

    private fun replaceByParentFragmentManager() {
        val history =
            StringBuilder()
                .append("replace via sfm, backStack: ${isAddToBackStack()}, setPrimary: ${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = HostFragment(history)
        val tag = fm::class.java.canonicalName
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activityFragmentContainer, fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }
}