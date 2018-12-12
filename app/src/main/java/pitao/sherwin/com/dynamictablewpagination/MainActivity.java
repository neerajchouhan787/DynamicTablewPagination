package pitao.sherwin.com.dynamictablewpagination;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TableLayout maintable;
    TableRow tableRow,rowHead;
    LinearLayout separator;
    TextView value;
    TextView textView;
    Pageable<Person> pageableArray;
    Button buttonNext;
    Button buttonPrev;
    TextView textViewPageCount;
    ArrayList<Person> myValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.tvID);
        maintable = (TableLayout) findViewById(R.id.main);
        rowHead = (TableRow) findViewById(R.id.rowHeader);
        buttonNext = (Button) findViewById(R.id.btnNext);
        buttonPrev = (Button) findViewById(R.id.btnPrevious);
        textViewPageCount = (TextView) findViewById(R.id.txtPageCount);
        myValues = new ArrayList<>();
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageableArray.setPage(pageableArray.getNextPage());
                tableRow.removeAllViews();
                displayPage();
                textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageableArray.setPage(pageableArray.getPreviousPage());
                tableRow.removeAllViews();
                displayPage();
                textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());

            }
        });


        for(int i=0; i<= 55; i++){
            myValues.add(new Person(i,"Sample" + i,"G","Sample" + i));
        }

        pageableArray = new Pageable<>(myValues);
        pageableArray.setPageSize(10);
        pageableArray.setPage(1);
        textViewPageCount.setText("Page " + pageableArray.getPage() + " of " + pageableArray.getMaxPages());
        displayPage();
    }

    public void displayPage() {
        maintable.removeAllViews();
        maintable.addView(rowHead);
        for (Person v : pageableArray.getListForPage()) {
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(String.valueOf(v.get_id()));
            tableRow.addView(value);

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(v.get_givenName());
            tableRow.addView(value);

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(v.get_middleName());
            tableRow.addView(value);

            value = new TextView(this);
            value.setLayoutParams(textView.getLayoutParams());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setText(v.get_surName());
            tableRow.addView(value);

            maintable.addView(tableRow);
            addSeparator();
        }
    }

    private void addSeparator() {
        Resources res = MainActivity.this.getResources();
        separator = new LinearLayout(MainActivity.this);
        separator.setOrientation(LinearLayout.VERTICAL);
        separator.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
        separator.setBackgroundColor(Color.parseColor("#5e7974"));
        separator.setDividerDrawable(res.getDrawable(R.drawable.radius_middle));
        maintable.addView(separator);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
