from PyQt5 import QtWidgets, QtCore
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.uic import loadUi
from PyQt5.QtCore import *
import sys
from PyQt5.QtGui import QPalette, QPixmap, QBrush
from PyQt5.QtGui import QFontDatabase, QFont
import startres_rc
import image_rc


class StartPage(QWidget):
    def __init__(self):
        super(StartPage, self).__init__()
        loadUi("StartPage.ui", self)

    def startButton(self):
       widget.setCurrentIndex(widget.currentIndex()+1)


class SelectFramePage(QWidget):
    selectNum = 0
    def __init__(self):
        super(SelectFramePage, self).__init__()
        loadUi("SelectFramePage.ui", self)

        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(True)
        #self.selectFrame1Btn.clicked.connect(self.selectFrame1)
        #self.selectFrame2Btn.clicked.connect(self.selectFrame2)
        #self.nextBtn.clicked.connect(self.changePage)

    def selectFrame1(self):
        self.frame2Check.setHidden(True)
        self.frame1Check.setHidden(False)
        self.selectNum = 1

    def selectFrame2(self):
        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(False)
        self.selectNum = 2

    def changePage(self):
        if self.selectNum != 0:
            self.frame1Check.setHidden(True)
            self.frame2Check.setHidden(True)
            widget.setCurrentIndex(2)
        else:
            print("select X")


class CostPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(CostPage, self).__init__()
        loadUi("CostPage.ui", self)
        self.costFinish = False
        #self.costTempBtn.clicked.connect(self.changePage)

        if self.costFinish:
            self.changePage()

    def changePage(self):
        if selectFramePage.selectNum == 1:
            self.command.emit(selectFramePage.selectNum)
            widget.setCurrentIndex(3)
        elif selectFramePage.selectNum == 2:
            self.command.emit(selectFramePage.selectNum)
            widget.setCurrentIndex(4)



class PicturePage1(QWidget):
    def __init__(self):
        super(PicturePage1, self).__init__()
        loadUi("PicturePage1.ui",self)
        #self.costTempBtn.clicked.connect(self.changePage)

        #사진 찍는 시간 타이머
        #self.time = 11
        self.lcdNumber.setDigitCount(2)
        costPage.command.connect(self.createTimer)


    def createTimer(self,num):
        if num == 1:
            self.time = 11
            self.timerVar = QTimer()
            self.timerVar.setInterval(1000)
            self.timerVar.timeout.connect(self.printTime)
            self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        #self.timer.setText(str(self.time))
        if self.time == 0:
            self.timerVar.stop()
        print(self.time)

    def changePage(self):
        self.timerVar.stop()
        widget.setCurrentIndex(5)


class PicturePage2(QWidget):
    def __init__(self):
        super(PicturePage2, self).__init__()
        loadUi("PicturePage2.ui",self)
        #self.costTempBtn.clicked.connect(self.changePage)
        #사진 찍는 시간 타이머
        #self.time = 11
        self.lcdNumber.setDigitCount(2)
        costPage.command.connect(self.createTimer)



    def createTimer(self, num):
        if num == 2:
            self.time = 11
            self.timerVar = QTimer()
            self.timerVar.setInterval(1000)
            self.timerVar.timeout.connect(self.printTime)
            self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        if self.time == 0:
            self.timerVar.stop()
        print(self.time)



    def changePage(self):
        self.timerVar.stop()
        widget.setCurrentIndex(5)


class SelectPicturePage(QWidget):
    command = QtCore.pyqtSignal(int)
    selectNum = 0
    def __init__(self):
        super(SelectPicturePage, self).__init__()
        loadUi("SelectPicturePage.ui",self)

        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        #self.selectPicture1Btn.clicked.connect(self.selectPicture1)
        #self.selectPicture2Btn.clicked.connect(self.selectPicture2)
        #self.selectPicture3Btn.clicked.connect(self.selectPicture3)
        #self.selectPicture4Btn.clicked.connect(self.selectPicture4)
        #self.nextBtn.clicked.connect(self.changePage)

    def selectPicture1(self):
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture1Check.setHidden(False)
        self.selectNum = 1

    def selectPicture2(self):
        self.picture1Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture2Check.setHidden(False)
        self.selectNum = 2

    def selectPicture3(self):
        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture3Check.setHidden(False)
        self.selectNum = 3

    def selectPicture4(self):
        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(False)
        self.selectNum = 4

    def changePage(self):
        if self.selectNum != 0:
            widget.setCurrentIndex(6)
            self.command.emit(1)
            self.picture1Check.setHidden(True)
            self.picture2Check.setHidden(True)
            self.picture3Check.setHidden(True)
            self.picture4Check.setHidden(True)



class BackgroundPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(BackgroundPage, self).__init__()
        print("bp")
        loadUi("BackgroundPage.ui", self)
        # self.costTempBtn.clicked.connect(self.changePage)
        # 사진 찍는 시간 타이머
        #self.time = 61
        self.lcdNumber.setDigitCount(2)
        selectPicturePage.command.connect(self.createTimer)

    def createTimer(self):
        self.time = 61
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        if self.time == 0:
            self.timerVar.stop()
            widget.setCurrentIndex(7)
            self.command.emit(1)

    def changePage(self):
        widget.setCurrentIndex(7)
        self.command.emit(1)


class MemoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    clear_command = QtCore.pyqtSignal(int)
    plus_command = QtCore.pyqtSignal(int)
    minus_command = QtCore.pyqtSignal(int)
    color_command = QtCore.pyqtSignal(int)
    save_command = QtCore.pyqtSignal(int)
    #pen_size = 5
    #pen_color = Qt.black
    def __init__(self):
        super(MemoPage, self).__init__()
        loadUi("MemoPage.ui", self)
        backgroundPage.command.connect(self.createTimer)
        self.memowidget = MyApp(self.clear_command, self.plus_command, self.minus_command, self.color_command, self.save_command)
        self.exlayout.addWidget(self.memowidget)
        self.flag = 1
        self.redBtn.setHidden(True)
        self.blueBtn.setHidden(True)
        self.blackBtn.setHidden(True)


    def plusSize(self):
        self.plus_command.emit(1)

    def minusSize(self):
        self.minus_command.emit(1)

    def changeColor(self):
        if self.flag == 0:
            self.redBtn.setHidden(True)
            self.blueBtn.setHidden(True)
            self.blackBtn.setHidden(True)
            self.flag = 1
        else:
            self.redBtn.setHidden(False)
            self.blueBtn.setHidden(False)
            self.blackBtn.setHidden(False)
            self.flag = 0

    def changeRed(self):
        self.color_command.emit(1)

    def changeBlue(self):
        self.color_command.emit(2)

    def changeBlack(self):
        self.color_command.emit(3)

    def clear(self):
        self.clear_command.emit(1)

    def createTimer(self):
        self.time = 180
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        if self.time == 0:
            self.timerVar.stop()
            self.changePage()

    def changePage(self):
        self.save_command.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송


class MyApp(QWidget):
    def __init__(self, clear_command, plus_command, minus_command, color_command, save_command):
        super().__init__()
        self.image = QImage(QSize(858, 598), QImage.Format_RGB32)
        self.image.fill(Qt.white)
        self.drawing = False
        clear_command.connect(self.clear_r)
        plus_command.connect(self.plusSize_r)
        minus_command.connect(self.minusSize_r)
        color_command.connect(self.color)
        save_command.connect(self.save)
        self.brush_size = 5
        self.brush_color = Qt.black
        self.last_point = QPoint()
        #self.initUI()
        ##self.cor = []
        ##self.sub_cor = []

    def color(self, num):
        if num == 1:
            self.brush_color = Qt.red
        elif num == 2:
            self.brush_color = Qt.blue
        elif num == 3:
            self.brush_color = Qt.black

    def clear_r(self, num):
        self.image.fill(Qt.white)
        self.update()

    def plusSize_r(self):
        self.brush_size += 1

    def minusSize_r(self):
        if self.brush_size >= 1:
            self.brush_size -= 1
        else:
            self.brush_size = 1

    def paintEvent(self, e):
        canvas = QPainter(self)
        canvas.drawImage(self.rect(), self.image, self.image.rect())

    def mousePressEvent(self, e):
        if e.button() == Qt.LeftButton:
            self.drawing = True
            self.last_point = e.pos()

    def mouseMoveEvent(self, e):
        if (e.buttons() & Qt.LeftButton) & self.drawing:
            painter = QPainter(self.image)
            painter.setPen(QPen(self.brush_color, self.brush_size, Qt.SolidLine, Qt.RoundCap))
            painter.drawLine(self.last_point, e.pos())
            self.last_point = e.pos()
            self.update()

    def mouseReleaseEvent(self, e):
        if e.button() == Qt.LeftButton:
            self.drawing = False
            ##self.cor.append(self.sub_cor[:])

    def save(self):
        fpath = './memo/memo1.jpg'
        self.image.save(fpath, "JPEG")
        self.image.fill(Qt.white)
        self.update()

###
class AddressPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(AddressPage, self).__init__()
        loadUi("AddressPage.ui", self)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송


class PrintPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(PrintPage, self).__init__()
        loadUi("PrintPage.ui", self)
        addressPage.command.connect(self.createTimer)
        #self.time = 3

    def createTimer(self):
        self.time = 3
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        print(self.time)
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)  # 신호 전송
            self.timerVar.stop()


class SelectPage(QWidget):
    command = QtCore.pyqtSignal(int)
    command2 = QtCore.pyqtSignal(int)#신호 생성
    def __init__(self):
        super(SelectPage, self).__init__()
        loadUi("SelectPage.ui", self)
        #self.videoBtn.clicked.connect(self.changePage)
        #self.skipBtn.clicked.connect(self.changePage2)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송

    def changePage2(self):
        widget.setCurrentIndex(widget.currentIndex()+3)
        self.command2.emit(1)


class ReadyPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(ReadyPage, self).__init__()
        loadUi("ReadyPage.ui", self)
        selectPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.lcdNumber.setDigitCount(1)
        #self.time = 6

    def createTimer(self):
        self.time = 6
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        print(self.time)
        self.lcdNumber.display(self.time)
        # self.Number.setText(str(self.time))
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)
            self.timerVar.stop()


class VideoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(VideoPage, self).__init__()
        loadUi("VideoPage.ui", self)
        readyPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.lcdNumber.setDigitCount(2)
        #self.time = 31
        # page 변환
        #self.nextBtn.clicked.connect(self.changePage)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1)  # 신호 전송
        self.timerVar.stop()

    def createTimer(self):
        print("a")
        self.time = 31
        self.timerVar = QTimer()
        #self.timerVar.timeout.connect(self.lcdEvent)
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        print("b")

    def printTime(self):
        self.time -= 1
        print(self.time)
        self.lcdNumber.display(self.time)
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.timerVar.stop()


class FinishPage(QWidget):
    def __init__(self):
        super(FinishPage, self).__init__()
        loadUi("FinishPage.ui", self)
        selectPage.command2.connect(self.createTimer)
        videoPage.command.connect(self.createTimer)
        #self.time = 6

    def createTimer(self):
        self.time = 6
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        if self.time == 0:
            widget.setCurrentIndex(0)
            self.timerVar.stop()

if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv)

    #폰트 바꾸기
    # fontDB = QFontDatabase()
    # fontDB.addApplicationFont("./HSSaemaul-Regular.ttf")
    # app.setFont(QFont('HS새마을체'))

    #화면 전환용 Widget 설정
    widget = QtWidgets.QStackedWidget()

    #레이아웃 인스턴스 생성
    startPage = StartPage()
    selectFramePage = SelectFramePage()
    costPage = CostPage()
    picturePage1 = PicturePage1()
    picturePage2 = PicturePage2()
    selectPicturePage = SelectPicturePage()
    backgroundPage = BackgroundPage()
    memoPage = MemoPage()
    addressPage = AddressPage()
    printPage = PrintPage()
    selectPage = SelectPage()
    readyPage = ReadyPage()
    videoPage = VideoPage()
    finishPage = FinishPage()

    #배경이미지
    # pixmap = QPixmap("./screenassets/background.png")
    # palette = QPalette()
    # palette.setBrush(QPalette.Background, QBrush(pixmap))
    # widget.setPalette(palette)

    #Widget 추가
    widget.addWidget(startPage)
    widget.addWidget(selectFramePage)
    widget.addWidget(costPage)
    widget.addWidget(picturePage1)
    widget.addWidget(picturePage2)
    widget.addWidget(selectPicturePage)
    widget.addWidget(backgroundPage)
    widget.addWidget(memoPage)
    widget.addWidget(addressPage)
    widget.addWidget(printPage)
    widget.addWidget(selectPage)
    widget.addWidget(readyPage)
    widget.addWidget(videoPage)
    widget.addWidget(finishPage)

    #프로그램 화면을 보여주는 코드
    #widget.setFixedHeight(796)
    #widget.setFixedWidth(1152)
    widget.setFixedHeight(850)
    widget.setFixedWidth(1530)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()