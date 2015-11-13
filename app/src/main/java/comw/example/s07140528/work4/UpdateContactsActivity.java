package comw.example.s07140528.work4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactsActivity extends Activity {
    private EditText nameET,mobileET,qqET,danweiET,addressET;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);
        nameET= (EditText) findViewById(R.id.name);
        mobileET= (EditText) findViewById(R.id.mobile);
        qqET= (EditText) findViewById(R.id.qq);
        danweiET= (EditText) findViewById(R.id.danwei);
        addressET= (EditText) findViewById(R.id.address);
        Bundle LocalBundle=getIntent().getExtras();
        int id=LocalBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        nameET.setText(user.getName());
        mobileET.setText(user.getMobile());
        qqET.setText(user.getQq());
        danweiET.setText(user.getDanwei());
        addressET.setText(user.getAdress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE,1,Menu.NONE,"保存");
        menu.add(Menu.NONE,2,Menu.NONE,"返回");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case 1://保存
                if (!nameET.getText().toString().equals("")){
                    user.setName(nameET.getText().toString());
                    user.setMobile(mobileET.getText().toString());
                    user.setQq(qqET.getText().toString());
                    user.setDanwei(danweiET.getText().toString());
                    user.setAdress(addressET.getText().toString());
                    ContactsTable ct=new ContactsTable(this);
                    //修改数据库联系人信息
                    if (ct.addDate(user)){
                        Toast.makeText(this, "添加成功！", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(this,"添加失败！",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this,"数据不能为空！",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
