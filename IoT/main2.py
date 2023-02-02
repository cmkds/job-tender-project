from PyQt5 import QtWidgets, QtCore
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
from PyQt5.QtCore import *
import sys
from PyQt5.QtGui import QPalette, QPixmap, QBrush
from PyQt5.QtGui import QFontDatabase, QFont
import startres_rc


class StartPage(QWidget):
    def __init__(self):
        super(StartPage, self).__init__()
        loadUi("startPage.ui", self)
        self.btnStart.clicked.connect(self.moveToSelectFrame)

    def moveToSelectFrame(self):
        widget.setCurrentIndex(1)


class SelectFramePage(QWidget):
    selectNum = 0
    def __init__(self):
        super(SelectFramePage, self).__init__()
        loadUi("selectFramePage.ui", self)

        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(True)
        self.selectFrame1Btn.clicked.connect(self.selectFrame1)
        self.selectFrame2Btn.clicked.connect(self.selectFrame2)
        self.nextBtn.clicked.connect(self.changePage)


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
            widget.setCurrentIndex(2)
        else:
            print("select X")


class CostPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(CostPage, self).__init__()
        loadUi("costPage.ui", self)
        self.costFinish = False
        self.costTempBtn.clicked.connect(self.changePage)

        if self.costFinish:
            self.changePage()

    def changePage(self):
        if selectframepage.selectNum == 1:
            self.command.emit(selectframepage.selectNum)
            widget.setCurrentIndex(3)
        elif selectframepage.selectNum == 2:
            self.command.emit(selectframepage.selectNum)
            widget.setCurrentIndex(4)



class PicturePage1(QWidget):
    def __init__(self):
        super(PicturePage1, self).__init__()
        loadUi("picturePage1.ui",self)
        self.costTempBtn.clicked.connect(self.changePage)

        #사진 찍는 시간 타이머
        self.time = 11
        self.lcdNumber.setDigitCount(2)
        costpage.command.connect(self.createTimer)


    def createTimer(self,num):
        if num == 1:
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
        widget.setCurrentIndex(5)


class PicturePage2(QWidget):
    def __init__(self):
        super(PicturePage2, self).__init__()
        loadUi("picturePage2.ui",self)
        self.costTempBtn.clicked.connect(self.changePage)
        #사진 찍는 시간 타이머
        self.time = 11
        self.lcdNumber.setDigitCount(2)
        costpage.command.connect(self.createTimer)


    def createTimer(self, num):
        if num == 2:
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
        widget.setCurrentIndex(5)


class SelectPicturePage(QWidget):
    selectNum = 0
    def __init__(self):
        super(SelectPicturePage, self).__init__()
        loadUi("selectPicturePage.ui",self)

        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.selectPicture1Btn.clicked.connect(self.selectPicture1)
        self.selectPicture2Btn.clicked.connect(self.selectPicture2)
        self.selectPicture3Btn.clicked.connect(self.selectPicture3)
        self.selectPicture4Btn.clicked.connect(self.selectPicture4)
        self.nextBtn.clicked.connect(self.changePage)

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
        pass


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
    startpage = StartPage()
    selectframepage = SelectFramePage()
    costpage = CostPage()
    picturepage1 = PicturePage1()
    picturepage2 = PicturePage2()
    selectpicturepage = SelectPicturePage()

    #배경이미지
    # pixmap = QPixmap("./screenassets/background.png")
    # palette = QPalette()
    # palette.setBrush(QPalette.Background, QBrush(pixmap))
    # widget.setPalette(palette)

    #Widget 추가
    widget.addWidget(startpage)
    widget.addWidget(selectframepage)
    widget.addWidget(costpage)
    widget.addWidget(picturepage1)
    widget.addWidget(picturepage2)
    widget.addWidget(selectpicturepage)


    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(600)
    widget.setFixedWidth(1024)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()