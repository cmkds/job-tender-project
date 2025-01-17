import requests
import xml.etree.ElementTree as ET

# uri = 'http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd'
uri = 'http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd'
service_key = 'LYn67ZcmpVcl8UZTD%2B697IwsAMAkKPidZkk7K2X6ROHRXJwy7HkftIdP6qit4dh5emCC4g8mnW7HFXruqfEYmQ%3D%3D'
#service_key_decoding = requests.utils.unquote(service_key)
service_key_decoding = 'LYn67ZcmpVcl8UZTD+697IwsAMAkKPidZkk7K2X6ROHRXJwy7HkftIdP6qit4dh5emCC4g8mnW7HFXruqfEYmQ=='
print('=============== 도로명 주소 & 지번 주소 & 우편번호 =======================')
print('1. 지번으로 검색\n2. 도로명으로 검색\n3. 우편번호\n')

select = input('검색 방법 선택 : ')

if select == '1':
    seach_se = 'dong'
    srchwrd = input('지번 입력(예: 주월동 408-1) : ')
elif select == '2':
    seach_se = 'road'
    srchwrd = input('도로명 입력(예: 서문대로 745) : ')
else:
    seach_se = 'post'
    srchwrd = input('우편번호 입력(예: 61725) : ')

payload = {'ServiceKey': service_key_decoding, 'searchSe': seach_se,
           'srchwrd': srchwrd, 'countPerPage': '100', 'currentPage': '1'}

resp = requests.get(uri, params=payload)
root = ET.fromstring(resp.text)

newAddressListAreaCd = root.findall("newAddressListAreaCd")

print('=============== 결과 출력 =======================')

for r in newAddressListAreaCd:
    print(f'우편번호 : {r.findtext("zipNo")}')
    print(f'도로명 주소 : {r.findtext("lnmAdres")}')
    print(f'지번 주소 : {r.findtext("rnAdres")}')
    print('--------------------------------------------------------------------')