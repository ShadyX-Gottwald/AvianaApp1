package skosana.avianaapp1.Presentation.Common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import skosana.avianaapp1.R

@Composable
fun BirdProfileCard(
    cornerShapeRadius: Dp = 16.dp,
    contentPadding: Dp = 16.dp,
    // 1. Simple Color Theming
    primaryColor: Color = Color(0xFF283542),
    secondaryColor: Color = Color(0xFF05D79B),
    tertiaryColor: Color = Color.White,
    // Parameters for ProfileHeader()
    imagePainter: Painter = rememberAsyncImagePainter(model = R.drawable.avianalogo),
    imageSize: Dp = 80.dp,
    imageShape: Shape = RoundedCornerShape(8.dp),
    locationText: String = "Location",
    titleText: String = "John Doe",
    dateText: String = "Date",
    titleFontSize: TextUnit = 18.sp,
    titleTextColor: Color = tertiaryColor,
    subtitleText: String = "Software Engineer",
    subtitleFontSize: TextUnit = 16.sp,
    subtitleTextColor: Color = Color(0xFF9E9FA0),
    headerBackgroundColor: Color = primaryColor,
    // Parameters for SocialMediaIcons()
    onClickIcon1: () -> Unit = {},
    onClickIcon2: () -> Unit = {},
    onClickIcon3: () -> Unit = {},
    onClickIcon4: () -> Unit = {},

    iconBackgroundColor: Color = secondaryColor,
    iconTintColor: Color = tertiaryColor,
    // Parameters for ProfileActionButtons()
    button1Text: String = "Follow",
    button1TextColor: Color = primaryColor,
    button1ContainerColor: Color = Color.Transparent,
    onButton1Clicked: () -> Unit = {},
    button2Text: String = "Contact",
    button2TextColor: Color = tertiaryColor,
    button2ContainerColor: Color = primaryColor,
    onButton2Clicked: () -> Unit = {},
    buttonsTextSize: TextUnit = 16.sp,
    buttonsCornerShape: Shape = RoundedCornerShape(12.dp)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(cornerShapeRadius)
    ) {
        Column {
            BirdCardComponent(
                imagePainter = imagePainter,
                imageSize = imageSize,
                imageShape = imageShape,
                titleText = titleText,
                dateText = dateText,
                titleFontSize = titleFontSize,
                titleTextColor = titleTextColor,
                subtitleText = subtitleText,
                subtitleFontSize = subtitleFontSize,
                subtitleTextColor = subtitleTextColor,
                backgroundColor = headerBackgroundColor,
                contentPadding = contentPadding
            )

//            ProfileActionButtons(
//                onClickButton1 = onButton1Clicked,
//                onClickButton2 = onButton2Clicked,
//                textButton1 = button1Text,
//                textColorButton1 = button1TextColor,
//                containerColorButton1 = button1ContainerColor,
//                textButton2 = button2Text,
//                textColorButton2 = button2TextColor,
//                containerColorButton2 = button2ContainerColor,
//                buttonsTextSize = buttonsTextSize,
//                buttonsCornerShape = buttonsCornerShape,
//                contentPadding = contentPadding
//            )
        }
    }
}

@Preview
@Composable
fun ProfileCardPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        BirdProfileCard()
    }
}

@Composable
fun ProfileActionButtons(
    buttonsTextSize: TextUnit = 16.sp,
    buttonsCornerShape: Shape = RoundedCornerShape(12.dp),
    contentPadding: Dp = 16.dp,
    textButton1: String = "Follow",
    textColorButton1: Color = Color(0xFF283542),
    containerColorButton1: Color = Color.Transparent,
    onClickButton1: () -> Unit = {},
    textButton2: String = "Contact",
    textColorButton2: Color = Color.White,
    containerColorButton2: Color = Color(0xFF283542),
    onClickButton2: () -> Unit = {}
) {
    Row(modifier = Modifier.padding(
        start = contentPadding,
        end = contentPadding,
        bottom = contentPadding
    )) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = { onClickButton1() },
            colors = ButtonDefaults.buttonColors(containerColor = containerColorButton1),
            border = getButtonBorder(containerColorButton1, textColorButton1),
            shape = buttonsCornerShape
        ) {
            Text(
                textButton1,
                color = textColorButton1,
                fontSize = buttonsTextSize,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(contentPadding))
        Button(
            modifier = Modifier.weight(1f),
            onClick = { onClickButton2() },
            colors = ButtonDefaults.buttonColors(containerColor = containerColorButton2),
            border = getButtonBorder(containerColorButton2, textColorButton2),
            shape = buttonsCornerShape
        ) {
            Text(
                textButton2,
                color = textColorButton2,
                fontSize = buttonsTextSize,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

// 1. Determining the Proper Border Style
fun getButtonBorder(containerColor: Color, textColor: Color): BorderStroke {
    return if (containerColor == Color.Transparent)
        BorderStroke(1.dp, textColor)
    else BorderStroke(0.dp, Color.Transparent)
}

@Preview
@Composable
fun ProfileActionButtonsPreview() {
    ProfileActionButtons()
}