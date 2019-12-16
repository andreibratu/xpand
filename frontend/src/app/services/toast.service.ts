import { Injectable } from '@angular/core';
import {ToastController} from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toastController: ToastController) {
    console.log('Hello ToastService Provider');
  }

  showToast(message: string, position: 'top' | 'bottom' | 'middle', duration: number) {
    const toastPromise = this.toastController.create({message, duration, position});

    toastPromise.then(toast => toast.present());
  }
}
