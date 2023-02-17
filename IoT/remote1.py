from gpiozero import Button
from signal import pause
from time import sleep


def clickBtn1():
    print("set1")
    sleep(0.5)
    pressed = False

def clickBtn2():
    print("set2")
    pressed = False
    #dsleep(0.5)

def clearBtn1():
    print("clear1")
    released = False
    sleep(0.5)

def clearBtn2():
    print("clear2")
    released = False
    #sleep(0.5)


button1 = Button(23)
button2 = Button(24)
button1.when_pressed = clickBtn1
button1.when_released = clearBtn1
button2.when_pressed = clickBtn2
button2.when_released = clearBtn2

pause()

