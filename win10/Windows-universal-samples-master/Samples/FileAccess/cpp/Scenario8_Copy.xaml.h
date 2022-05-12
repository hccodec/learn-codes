//*********************************************************
//
// Copyright (c) Microsoft. All rights reserved.
// This code is licensed under the MIT License (MIT).
// THIS CODE IS PROVIDED *AS IS* WITHOUT WARRANTY OF
// ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING ANY
// IMPLIED WARRANTIES OF FITNESS FOR A PARTICULAR
// PURPOSE, MERCHANTABILITY, OR NON-INFRINGEMENT.
//
//*********************************************************

#pragma once

#include "pch.h"
#include "Scenario8_Copy.g.h"
#include "MainPage.xaml.h"

namespace SDKTemplate
{
    /// <summary>
    /// Copying a file.
    /// </summary>
    [Windows::Foundation::Metadata::WebHostHidden]
    public ref class Scenario8 sealed
    {
    public:
        Scenario8();
        void OnNavigatedTo(Windows::UI::Xaml::Navigation::NavigationEventArgs^ e) override;

    private:
        MainPage^ rootPage = MainPage::Current;

        void CopyFileButton_Click(Platform::Object^ sender, Windows::UI::Xaml::RoutedEventArgs^ e);
    };
}
