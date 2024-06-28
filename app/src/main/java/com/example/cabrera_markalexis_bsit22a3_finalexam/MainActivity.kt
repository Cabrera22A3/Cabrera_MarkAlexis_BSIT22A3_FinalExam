package com.example.cabrera_markalexis_bsit22a3_finalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cabrera_markalexis_bsit22a3_finalexam.ui.theme.Cabrera_MarkAlexis_BSIT22A3_FinalExamTheme

public val Icons.Filled.ExpandMore: ImageVector
    get() {
        if (_expandMore != null) {
            return _expandMore!!
        }
        _expandMore = materialIcon(name = "Filled.ExpandMore") {
            materialPath {
                moveTo(16.59f, 8.59f)
                lineTo(12.0f, 13.17f)
                lineTo(7.41f, 8.59f)
                lineTo(6.0f, 10.0f)
                lineToRelative(6.0f, 6.0f)
                lineToRelative(6.0f, -6.0f)
                close()
            }
        }
        return _expandMore!!
    }

private var _expandMore: ImageVector? = null

public val Icons.Filled.ExpandLess: ImageVector
    get() {
        if (_expandLess != null) {
            return _expandLess!!
        }
        _expandLess = materialIcon(name = "Filled.ExpandLess") {
            materialPath {
                moveTo(12.0f, 8.0f)
                lineToRelative(-6.0f, 6.0f)
                lineToRelative(1.41f, 1.41f)
                lineTo(12.0f, 10.83f)
                lineToRelative(4.59f, 4.58f)
                lineTo(18.0f, 14.0f)
                close()
            }
        }
        return _expandLess!!
    }

private var _expandLess: ImageVector? = null

//same idea sa Activity 8 pero tinanggal ko na po yung title since diko naman siya need
enum class PKMNScreen{
    homeScreen,
    gameScreen,
    aboutScreen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cabrera_MarkAlexis_BSIT22A3_FinalExamTheme {
                MainApp()
            }
        }
    }
}

//Dito makikita yung manipulation ng Rows and Cols para sa kabuuhan
@Composable
fun MainApp(){
    Column {
        Row (
            modifier = Modifier
                .background(color = Color.Blue)
                .height(100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            HeaderBackground()
        }
        Row (
            modifier = Modifier
                .background(color = Color.DarkGray)
                .padding(all = 8.dp)
                .fillMaxWidth()
                .fillMaxHeight(.92f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            SwitchScreen()
        }
        Row (
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(all = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ){
            Footer()
        }
    }
}

//Dito yung paggamit ng Nav Host
@Composable
fun SwitchScreen(navController: NavHostController = rememberNavController()){
    //triny ko gawing parang "string" yung pag call sa fun functions, pwede po pala yunnn
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PKMNScreen.valueOf(
        backStackEntry?.destination?.route ?: "homeScreen"
    )
    NavHost(
        navController = navController,
        startDestination = "homeScreen"
    ) {
        composable("homeScreen"){ BodyContentHome(navController = navController) }
        composable("gameScreen"){ BodyContentGame(navController = navController) }
        composable("aboutScreen"){ BodyContentAbout(navController = navController) }
    }
}

@Composable
fun BodyContentHome(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            //Image Who's that Pokemon?
            Image(
                painter = painterResource(id = R.drawable.wtpokemon),
                contentDescription = "WTPokemon_Home"
            )
        }
        Row {
            //Start now Button
            Button(
                onClick = {navController.navigate("gameScreen")}
            ) {
                Text(stringResource(R.string.startnow_btn))
            }
            Spacer(//para siyang margin para sa button
                modifier = Modifier
                    .height(16.dp)
            )
        }
        Row {
            //About Button
            Button(
                //ganto po yung pag-route once the button was clicked
                onClick = {navController.navigate("aboutScreen")}
            ) {
                Text(stringResource(R.string.about_btn))
            }
            Spacer(//para siyang margin para sa button
                modifier = Modifier
                    .height(16.dp)
            )
        }
    }
}

@Composable
fun BodyContentAbout(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Row {
            //Image Who's that Pokemon?
            Image(
                painter = painterResource(id = R.drawable.wtpokemon),
                contentDescription = "WTPokemon_Home"
            )
        }
        Row {
            //Name, Section, Subject.
            Text(
                text = "Name: Mark Alexis Jaudian Cabrera\nSection: BSIT-22A3\nSubject: Mobile Computing",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .wrapContentSize(Alignment.TopCenter)
                    .align(Alignment.Top)
            )
        }
        Spacer(//para siyang margin para sa button
            modifier = Modifier
                .height(16.dp)
        )
        Row{
            //ss
            Item(titleChu = stringResource(R.string.titleDropDownDescription1),
                descChu = stringResource(R.string.descDropDownDescription1))
        }
        Spacer(//para siyang margin para sa button
                modifier = Modifier
                    .height(16.dp)
                )
        Row{
            //ss
            Item(titleChu = stringResource(R.string.asdf),
                descChu = stringResource(R.string.ghjkl))
        }
        Spacer(//para siyang margin para sa button
            modifier = Modifier
                .height(16.dp)
        )
        Row {
            //Back Button
            Button(
                onClick = {navController.navigate("homeScreen")}
            ) {
                Text(stringResource(R.string.back_btn))
            }
            Spacer(//para siyang margin para sa button
                modifier = Modifier
                    .height(16.dp)
            )
        }
    }
}

@Composable//dito nangyayari yung pagdisplay ng POKEMON
fun BodyContentGame(modifier: Modifier = Modifier, navController: NavController) {
    var answerKey by remember { mutableStateOf("Press Check Button") }
    var answer by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(1) }
    var scoreboard by remember { mutableStateOf(0) }

    val imageResource = when (result) {
        1 -> R.drawable.pikachu
        2 -> R.drawable.bulbasaur
        3 -> R.drawable.squirtle
        else -> R.drawable.squirtle
    }
    var pokemonName by remember { mutableStateOf("") }
    if(result == 1){
        pokemonName = stringResource(R.string.pikachu)
    }else if(result == 2){
        pokemonName = stringResource(R.string.bulbasaur)
    }else if(result == 3){
        pokemonName = stringResource(R.string.squirtle)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = {}){
                Text("Score: ${scoreboard}")
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxHeight(.3f)
                .fillMaxWidth(.8f)
        ){
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = result.toString()
            )
            //para siyang margin para sa button
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
        }
        Row {
            TextField(
                value = answer, // Set the current value from the state variable
                onValueChange = { answer = it }, // Update state on value change
                label = { Text("Enter your answer in all caps..") },
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            Spacer(//para siyang margin para sa button
                modifier = Modifier
                    .height(16.dp)
            )
        }
        Row {
            Column {
                Button(onClick = {
                    if (answer == pokemonName) {
                        answerKey = "CORRECT"
                        scoreboard += 1
                        // Modifier.background(Color.Green)
                    } else {
                        answerKey = "WRONG"
                        scoreboard -= 1
                        //Modifier.background(Color.Red)
                    }
                }
                ){
                    Text(stringResource(R.string.check_btn))
                }
            }
            Column {
                Button(onClick = {}){
                    Text(answerKey)
                }
            }
        }
        Row {
            Column {
                Button(onClick = {navController.navigate("homeScreen")}){
                    Text(stringResource(R.string.exit_btn))
                }
            }
            Column {
                //dito nangyayari yung pagbigay ng randomise numbers from 1-6
                Button(onClick = {result = (1..3).random()}) {
                    //since naglagay ng button ung text yung nagsisilbi kung para saan yung button
                    Text(stringResource(R.string.random_btn))
                }
                //para siyang margin para sa button
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
            }
        }
    }
}

@Composable
fun HeaderBackground(){
    Row {
        Column {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "pokeball-1",
                modifier = Modifier
                    .height(80.dp)
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp)
            )
        }
        Column {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.pokemon),
                    contentDescription = "pokemon",
                    modifier = Modifier
                        .height(80.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 35.dp)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.quiz_game),
                    contentDescription = "quiz_game",
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Column {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "pokeball-2",
                modifier = Modifier
                    .height(80.dp)
                    .align(Alignment.End)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun Footer(){
    Column {
        Row {
            Text(
                text = "All Rights Reserved 2024. @PokemonQuizGame!",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .wrapContentSize(Alignment.TopCenter)
                    .align(Alignment.Top)
            )
        }
    }
}

//Dropdown

@Composable
fun Item(
    titleChu: String,
    descChu:  String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = titleChu,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopCenter)
                        .align(Alignment.Top)
                )
                Spacer(Modifier.weight(1f))
                ItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                LazyColumn(modifier = Modifier.fillMaxSize(.8f)) {
                    item {
                        //Description about the game.
                        Text(
                            text = descChu,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Justify,
                            color = Color.Black,
                            modifier = Modifier
                                .wrapContentSize(Alignment.TopCenter)
                                .padding(start = 20.dp, end = 20.dp, top = 15.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun ItemButton(expanded: Boolean,onClick: () -> Unit,modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Cabrera_MarkAlexis_BSIT22A3_FinalExamTheme {
        MainApp()
    }
}