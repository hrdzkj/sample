1.andriod 6.0后popwindow不能响应onkeylistner,不能监听外部回退按钮。 有多交互多页面的
弹窗，建议使用BottomSheet/BottomSheetDialog/BottomSheetDialogFragment DialogFragment代替
可以使用DialogFragment（https://github.com/fccaikai/BottomMenuTutorial.git）。
监听返回键：
@Override
public void onResume() {
    super.onResume();
    // 监听返回键
    getView().setFocusableInTouchMode(true);
    getView().requestFocus();
    getView().setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK){
                Toast.makeText(getActivity(), "按了返回键", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    });
}