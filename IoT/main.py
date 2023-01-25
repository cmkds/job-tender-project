from PyQt5 import QtWidgets
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
from PyQt5.QtGui import QPalette, QPixmap, QBrush
import sys
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
    def __init__(self):
        super(SelectFramePage, self).__init__()
        loadUi("selectFramePage.ui", self)
        self.selectFlag = False

        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(True)
        self.selectFrame1Btn.clicked.connect(self.selectFrame1)
        self.selectFrame2Btn.clicked.connect(self.selectFrame2)
        self.nextBtn.clicked.connect(self.changePage)


    def selectFrame1(self):
        self.frame2Check.setHidden(True)
        self.frame1Check.setHidden(False)
        self.selectFlag = True

    def selectFrame2(self):
        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(False)
        self.selectFlag = True

    def changePage(self):
        if self.selectFlag:
            widget.setCurrentIndex(2)
        else:
            print("select X")


class CostPage(QWidget):
    def __init__(self):
        super(CostPage, self).__init__()
        loadUi("costPage.ui", self)


class PicturePage(QWidget):
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
    picturepage = PicturePage()


    #배경이미지
    # pixmap = QPixmap("./screenassets/background.png")
    # palette = QPalette()
    # palette.setBrush(QPalette.Background, QBrush(pixmap))
    # widget.setPalette(palette)

    #Widget 추가
    widget.addWidget(startpage)
    widget.addWidget(selectframepage)
    widget.addWidget(costpage)
    widget.addWidget(picturepage)


    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(600)
    widget.setFixedWidth(1024)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()