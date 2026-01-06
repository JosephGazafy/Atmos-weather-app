from kivy.app import App
from kivy.uix.label import Label
from jnius import autoclass

class AtmosApp(App):
    def build(self):
        # Start the native Android service
        service = autoclass('org.sovereign.mesh.ServiceAtmos_core')
        service.start(App.get_running_app().get_package_domain(), 'Atmos Core Service')
        
        return Label(text='ATMOS CORE: SOVEREIGN STATUS [120/100]')

if __name__ == '__main__':
    AtmosApp().run()

